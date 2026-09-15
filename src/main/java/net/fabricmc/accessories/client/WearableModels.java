package net.fabricmc.accessories.client;

/** Clothing silhouettes and fittings, independent of item icons and gameplay state. */
final class WearableModels {
    // Bare waist, leggings, chestplate: only the waist changes; the hem keeps leg clearance.
    static final WearableMesh[][] SKIRTS = {
            {skirt(false, 0), skirt(false, 0.5f), skirt(false, 1)},
            {skirt(true, 0), skirt(true, 0.5f), skirt(true, 1)}
    };
    static final WearableMesh[] NECKLACES = {
            necklace(0), necklace(1), necklace(2), necklace(3), necklace(4), necklace(5)
    };
    static final WearableMesh SCARF = scarf();
    static final WearableMesh EARS = ears();
    static final WearableMesh GLOVE = glove();
    static final WearableMesh SKATES = skates();
    static final WearableMesh[] BOOTS = {boots(0), boots(1), boots(2), boots(3), boots(4)};

    private WearableModels() {}

    private static WearableMesh skirt(boolean seated, float padding) {
        WearableMesh mesh = new WearableMesh();
        mesh.fabric(0x30323c).tube(9.8f, 11.2f, 4.25f + padding, 2.3f + padding, 4.4f + padding, 2.5f + padding, 0.3f);
        // Alternating radii create pleats; the rounded rectangle clears both thighs.
        float[][] top = new float[32][2], hem = new float[32][2], bottom = new float[32][2];
        float[] topY = new float[32], hemY = new float[32], bottomY = new float[32];
        for (int i = 0; i < top.length; i++) {
            double angle = 2 * Math.PI * i / top.length - Math.PI / 2;
            float x = (float) Math.cos(angle), z = (float) Math.sin(angle);
            float square = 1 / (float) Math.pow(Math.pow(Math.abs(x), 4) + Math.pow(Math.abs(z), 4), 0.25);
            float pleat = i % 2 == 0 ? 1 : 0.95f;
            top[i] = new float[]{x * square * (4.35f + padding), z * square * (2.5f + padding)};
            float depth = seated && z < 0 ? 12 : 6.1f;
            hem[i] = new float[]{x * square * 6.8f * pleat, z * square * depth * pleat};
            bottom[i] = new float[]{hem[i][0] * 1.02f, hem[i][1] * 1.02f};
            topY[i] = seated ? 11.15f + Math.min(z, 0) * 1.6f : 11.15f;
            hemY[i] = seated ? 14.8f + Math.min(z, 0) * 5 : 17;
            bottomY[i] = hemY[i] + 0.7f;
        }
        mesh.fabric(0x444854).shell(topY, hemY, top, hem, 0.22f);
        mesh.fabric(0xe7e1d6).shell(hemY, bottomY, hem, bottom, 0.24f);
        mesh.fabric(0xf4ede3).box(-0.8f, 10, -2.7f - padding, 0.8f, 11, -2.35f - padding);
        return mesh;
    }

    private static WearableMesh scarf() {
        WearableMesh mesh = new WearableMesh();
        mesh.fabric(0xa82d39).tube(0.8f, 2.2f, 4.9f, 3.4f, 5.2f, 3.7f, 0.55f);
        mesh.fabric(0xd9494d).tube(1.2f, 1.7f, 5.05f, 3.55f, 5.15f, 3.65f, 0.25f);
        mesh.fabric(0xbf3540).box(1.9f, 1.3f, -4.5f, 4.1f, 3.0f, -3.4f);
        mesh.fabric(0xa82d39).box(2.1f, 1.8f, -3.8f, 3.6f, 6.7f, -3.35f);
        mesh.fabric(0x8c2531).box(0.9f, 1.8f, -3.75f, 2.3f, 5.1f, -3.3f);
        mesh.fabric(0xe5b7a2).box(2.08f, 6.05f, -3.84f, 3.62f, 6.55f, -3.3f);
        return mesh;
    }

    private static WearableMesh ears() {
        WearableMesh mesh = new WearableMesh();
        mesh.fabric(0x393344).box(-4.8f, -9.35f, -1, 4.8f, -8.95f, 1);
        for (int side : new int[]{-1, 1}) {
            float x = side * 3.1f;
            mesh.fabric(0x393344).box(x - 1.45f, -11.4f, -0.9f, x + 1.45f, -9.3f, 0.9f);
            mesh.box(x - 0.9f, -12.5f, -0.75f, x + 0.9f, -11.4f, 0.75f);
            mesh.box(x - 0.4f, -13.2f, -0.5f, x + 0.4f, -12.5f, 0.5f);
            mesh.fabric(0xc788b5).box(x - 0.85f, -11.1f, -0.98f, x + 0.85f, -9.7f, -0.9f);
            mesh.box(x - 0.4f, -12.05f, -0.83f, x + 0.4f, -11.1f, -0.75f);
        }
        return mesh;
    }

    private static WearableMesh glove() {
        WearableMesh mesh = new WearableMesh();
        mesh.fabric(0x49413b).tube(5.7f, 8.1f, 3.25f, 3.25f, 3.2f, 3.2f, 0.45f);
        mesh.metal(0xc3a76f).box(-3.15f, 7.8f, -3.15f, 3.15f, 11.35f, 3.15f);
        mesh.metal(0x706150).box(-3.4f, 6.1f, -3.45f, 3.4f, 7.3f, -3.1f);
        for (int finger = 0; finger < 4; finger++) {
            float x = -2.9f + finger * 1.5f;
            mesh.metal(0xe3c88c).box(x, 8.0f, -3.55f, x + 1.15f, 11.0f, -3.1f);
        }
        mesh.metal(0xa48a5d).box(3.05f, 7.6f, -1.8f, 4.0f, 9.8f, 0.3f);
        return mesh;
    }

    private static WearableMesh boots(int style) {
        int[] colors = {0x66854d, 0x43834b, 0x9ab7d9, 0x376c9d, 0x612f2e};
        int[] trims = {0xb8c7a4, 0xe7c373, 0xd3e8ef, 0x7ccbd7, 0xd99243};
        float top = style == 4 ? 1.2f : style == 3 ? 3.2f : 5.0f;
        WearableMesh mesh = new WearableMesh();
        mesh.fabric(colors[style]).tube(top, 10.6f, 3.15f, 3.15f, 3.1f, 3.15f, 0.32f);
        mesh.fabric(trims[style]).tube(top, top + 0.9f, 3.4f, 3.4f, 3.4f, 3.4f, 0.42f);
        mesh.fabric(colors[style]).box(-3.1f, 9.0f, -4.3f, 3.1f, 12.9f, 3.15f);
        mesh.fabric(0x34363b).box(-3.2f, 12.6f, -4.45f, 3.2f, 13.2f, 3.25f);
        mesh.fabric(trims[style]).box(-2.1f, 9.05f, -4.48f, 2.1f, 10.0f, -4.3f);
        if (style == 0) {
            for (int coil = 0; coil < 3; coil++) {
                float y = 6.2f + coil * 1.2f;
                mesh.metal(0xc1c8b0).tube(y, y + 0.4f, 3.5f, 3.5f, 3.5f, 3.5f, 0.3f);
            }
        } else if (style == 1 || style == 2) {
            for (int side : new int[]{-1, 1}) {
                float x = side * 3.55f;
                for (int feather = 0; feather < 3; feather++) {
                    mesh.fabric(trims[style]).box(x - 0.3f, 6.2f - feather * 0.8f, 1.5f + feather,
                            x + 0.3f, 8.5f - feather * 0.55f, 3.6f + feather);
                }
            }
        } else {
            mesh.metal(trims[style]).box(-1.2f, top + 1.4f, -3.5f, 1.2f, top + 2.5f, -3.15f);
            if (style == 4) mesh.metal(0x975c38).box(-2.8f, 9.9f, -4.6f, 2.8f, 11.5f, -4.3f);
        }
        return mesh;
    }


    private static WearableMesh necklace(int style) {
        WearableMesh mesh = new WearableMesh();
        int[] chain = {0xb49a6b, 0xc8dbe4, 0xe0bf68, 0xe8b854, 0xc8dbe4, 0xc5a3e6};
        int[] gem = {0, 0x4bd0c9, 0x3269c4, 0xffa52f, 0xa9d6f4, 0xb875ed};
        float length = 3.4f + (style % 3) * 1.5f;
        // Stones sit beside the existing charms when several necklaces are equipped.
        float charmX = style >= 3 ? -2 : 0;
        float z = -2.2f - style * 0.08f;
        mesh.metal(chain[style]).tube(0.15f, 0.45f, 4.2f, -z, 4.2f, -z, 0.18f);
        for (int side : new int[]{-1, 1}) {
            float halfLink = (0.6f - side * charmX / 5) / 2 + 0.08f;
            for (int link = 0; link < 6; link++) {
                float x = side * (3.5f - link * 0.6f) + charmX * link / 5;
                float y = 0.3f + link * length / 6;
                mesh.box(x - halfLink, y, z - 0.16f, x + halfLink, y + length / 6 + 0.12f, z);
            }
        }
        mesh.metal(chain[style]).box(charmX - 0.7f, length, z - 0.35f, charmX + 0.7f, length + 1.4f, z);
        if (style == 0) {
            // Bone teeth identify the Monster Necklace without using its item icon.
            for (int tooth = -1; tooth <= 1; tooth++) {
                mesh.fabric(0xe9dfc2).box(tooth * 0.8f - 0.25f, length + 0.3f, z - 0.48f,
                        tooth * 0.8f + 0.25f, length + 1.8f - Math.abs(tooth) * 0.4f, z - 0.2f);
            }
        } else {
            mesh.fabric(gem[style]).box(charmX - 0.48f, length + 0.2f, z - 0.5f,
                    charmX + 0.48f, length + 1.15f, z - 0.35f);
            mesh.box(charmX - 0.22f, length + 1.15f, z - 0.45f, charmX + 0.22f, length + 1.6f, z - 0.3f);
        }
        return mesh;
    }

    private static WearableMesh skates() {
        WearableMesh mesh = new WearableMesh();
        mesh.fabric(0x397eb7).tube(5.2f, 10.6f, 3.2f, 3.2f, 3.15f, 3.2f, 0.35f);
        mesh.fabric(0xa9dce9).tube(5.2f, 6.3f, 3.45f, 3.45f, 3.45f, 3.45f, 0.4f);
        mesh.fabric(0x397eb7).box(-3.15f, 9, -4.35f, 3.15f, 12.9f, 3.2f);
        for (int lace = 0; lace < 3; lace++) {
            float y = 7 + lace * 0.8f;
            mesh.fabric(0xd6eef2).box(-1.2f, y, -3.4f, 1.2f, y + 0.3f, -3.2f);
        }
        mesh.fabric(0x4a5664).box(-3.25f, 12.75f, -4.55f, 3.25f, 13.2f, 3.3f);
        mesh.metal(0x899eac).box(-0.6f, 13.2f, -2.9f, 0.6f, 13.7f, -1.7f);
        mesh.box(-0.6f, 13.2f, 1.2f, 0.6f, 13.7f, 2.3f);
        mesh.metal(0xd7edf3).box(-0.3f, 13.6f, -4.6f, 0.3f, 14.05f, 3.8f);
        return mesh;
    }
}
