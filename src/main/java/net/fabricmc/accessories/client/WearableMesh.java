package net.fabricmc.accessories.client;

import net.minecraft.src.Minecraft;
import net.minecraft.src.ResourceLocation;
import net.minecraft.src.Tessellator;

import java.util.ArrayList;
import java.util.List;

/** Reusable textured meshes, built once in player-model pixels. */
final class WearableMesh {
    private static final ResourceLocation[] TEXTURES = {
            new ResourceLocation("accessories", "textures/models/cloth.png"),
            new ResourceLocation("accessories", "textures/models/metal.png")
    };
    record Face(float[] vertices, float nx, float ny, float nz, float u, float v, int color, int material) {}
    final List<Face> faces = new ArrayList<>();
    private int color = 0xffffff;
    private int material;

    WearableMesh fabric(int color) { this.color = color; material = 0; return this; }
    WearableMesh metal(int color) { this.color = color; material = 1; return this; }

    void quad(float... p) {
        float ax = p[3] - p[0], ay = p[4] - p[1], az = p[5] - p[2];
        float bx = p[9] - p[0], by = p[10] - p[1], bz = p[11] - p[2];
        float nx = ay * bz - az * by, ny = az * bx - ax * bz, nz = ax * by - ay * bx;
        float length = (float) Math.sqrt(nx * nx + ny * ny + nz * nz);
        float u = (float) Math.sqrt(bx * bx + by * by + bz * bz) / 16;
        float v = (float) Math.sqrt(ax * ax + ay * ay + az * az) / 16;
        faces.add(new Face(p, nx / length, ny / length, nz / length, u, v, color, material));
    }

    void box(float x0, float y0, float z0, float x1, float y1, float z1) {
        quad(x0,y0,z0, x0,y1,z0, x1,y1,z0, x1,y0,z0);
        quad(x1,y0,z1, x1,y1,z1, x0,y1,z1, x0,y0,z1);
        quad(x0,y0,z1, x0,y1,z1, x0,y1,z0, x0,y0,z0);
        quad(x1,y0,z0, x1,y1,z0, x1,y1,z1, x1,y0,z1);
        quad(x0,y0,z1, x0,y0,z0, x1,y0,z0, x1,y0,z1);
        quad(x0,y1,z0, x0,y1,z1, x1,y1,z1, x1,y1,z0);
    }

    /** Open garment tube with thickness, rather than a solid box through the body. */
    void tube(float y0, float y1, float x0, float z0, float x1, float z1, float thickness) {
        float[][] top = {{-x0,-z0}, {x0,-z0}, {x0,z0}, {-x0,z0}};
        float[][] bottom = {{-x1,-z1}, {x1,-z1}, {x1,z1}, {-x1,z1}};
        shell(y0, y1, top, bottom, thickness);
    }

    void shell(float y0, float y1, float[][] top, float[][] bottom, float thickness) {
        float[] topY = new float[top.length], bottomY = new float[top.length];
        java.util.Arrays.fill(topY, y0);
        java.util.Arrays.fill(bottomY, y1);
        shell(topY, bottomY, top, bottom, thickness);
    }

    void shell(float[] topY, float[] bottomY, float[][] top, float[][] bottom, float thickness) {
        for (int i = 0; i < top.length; i++) {
            int j = (i + 1) % top.length;
            float[] a = top[i], b = bottom[i], c = bottom[j], d = top[j];
            float[] ai = inset(a, thickness), bi = inset(b, thickness);
            float[] ci = inset(c, thickness), di = inset(d, thickness);
            quad(a[0],topY[i],a[1], b[0],bottomY[i],b[1], c[0],bottomY[j],c[1], d[0],topY[j],d[1]);
            quad(di[0],topY[j],di[1], ci[0],bottomY[j],ci[1], bi[0],bottomY[i],bi[1], ai[0],topY[i],ai[1]);
            quad(ai[0],topY[i],ai[1], a[0],topY[i],a[1], d[0],topY[j],d[1], di[0],topY[j],di[1]);
            quad(b[0],bottomY[i],b[1], bi[0],bottomY[i],bi[1], ci[0],bottomY[j],ci[1], c[0],bottomY[j],c[1]);
        }
    }

    private static float[] inset(float[] point, float thickness) {
        float scale = 1 - thickness / Math.max(Math.abs(point[0]), Math.abs(point[1]));
        return new float[]{point[0] * scale, point[1] * scale};
    }

    void render() {
        Tessellator tess = Tessellator.instance;
        for (int texture = 0; texture < TEXTURES.length; texture++) {
            boolean started = false;
            for (Face face : faces) {
                if (face.material != texture) continue;
                if (!started) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURES[texture]);
                    tess.startDrawingQuads();
                    started = true;
                }
                tess.setColorOpaque_I(face.color);
                tess.setNormal(face.nx, face.ny, face.nz);
                for (int vertex = 0; vertex < 4; vertex++) {
                    int offset = vertex * 3;
                    tess.addVertexWithUV(face.vertices[offset], face.vertices[offset + 1], face.vertices[offset + 2],
                            vertex >= 2 ? face.u : 0, vertex == 1 || vertex == 2 ? face.v : 0);
                }
            }
            if (started) tess.draw();
        }
    }
}
