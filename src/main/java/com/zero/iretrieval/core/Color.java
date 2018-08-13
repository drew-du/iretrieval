package com.zero.iretrieval.core;

/**
 * 这个类提供对颜色的静态方法，不提倡实例化使用，该系统也不需要对颜色有什么需要实例化的操作
 * @author z
 *
 */
public class Color {
	private int[] rgb = null;
	private float[] hsv = null;
	public Color() {}
	public Color(int r, int g, int b) {
		this.rgb = new int[] {r, g, b};
	}
	
	public Color(float h, float s, float v) {
		this.hsv = new float[] {h, s, v};
	}
	
	public static int[] rgb(int rgb) {
		int value = 0xff000000 | rgb;
		return new int[] {(value >> 16) & 0xFF, (value >> 16) & 0xFF, (value >> 0) & 0xFF};
	}
	
	public int[] rgb() {
		if(rgb == null && hsv != null)
			this.rgb = HSV2RGB(hsv[0], hsv[1], hsv[2]);
		return this.rgb;
	}
	
	public float[] hsv() {
		if(hsv == null && rgb != null)
			this.hsv = RGB2HSV(rgb[0], rgb[1], rgb[2]);
		return this.hsv;
	}
	public static float[] rgb2hsv(int rgb) {
		int value = 0xff000000 | rgb;
		int[] rgbArr = new int[] {(value >> 16) & 0xFF, (value >> 8) & 0xFF, (value >> 0) & 0xFF};
		return RGB2HSV(rgbArr[0], rgbArr[1], rgbArr[2], null);
	}
	
	public static float[] RGB2HSV(int r, int g, int b, float[] values) {
        float hue, saturation, brightness;
        if (values == null) {
        	values = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        values[0] = hue;
        values[1] = saturation;
        values[2] = brightness;
        return values;
    }
	
	public static float[] RGB2HSV(int r, int g, int b) {
		float R = r / 255f;
		float G = g / 255f;
		float B = b / 255f;
		float H = 0;
		float max = R > G ? R > B ? R : B : G > B ? G : B;
		float min = R < G ? R < B ? R : B : G < B ? G : B;
		if (max == min)
			return new float[] { 0, 0, max };
		if (max == R)
			H = (G - B) / (max - min);
		else if (max == G)
			H = 2 + (B - R) / (max - min);
		else if (max == B)
			H = 4 + (R - G) / (max - min);
		H *= 60;
		if (H < 0)
			H = H + 360;
		return new float[] { H, (max - min) / max, max };
	}

	public static int[] HSV2RGB(float h, float s, float v) {
		float f, p, q, t;
		if (s == 0) {
			int V = (int) Math.ceil(v * 255 - 0.5);
			return new int[] { V, V, V };
		}
		h /= 60;
		int i = (int) Math.floor(h);
		f = h - i;
		v = v * 255;
		p = v * (1 - s);
		q = v * (1 - s * f);
		t = v * (1 - s * (1 - f));
		switch (i) {
		case 0:
			return new int[] { (int) v, (int) t, (int) p };
		case 1:
			return new int[] { (int) q, (int) v, (int) p };
		case 2:
			return new int[] { (int) p, (int) v, (int) t };
		case 3:
			return new int[] { (int) p, (int) q, (int) v };
		case 4:
			return new int[] { (int) t, (int) p, (int) v };
		default: // case 5:
			return new int[] { (int) v, (int) p, (int) q };
		}
	}
}
