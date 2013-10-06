package com.magic.gui;

import net.minecraft.src.Gui;
import org.lwjgl.opengl.GL11;

public class Methods extends Gui{
	
	/*public static void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) {
        drawVerticalLine(x, y, y1 -1, borderC);
        drawVerticalLine(x1 - 1, y, y1 - 1, borderC);
        drawHorizontalLine(x, x1 - 1, y, borderC);
        drawHorizontalLine(x, x1 - 1, y1 -1, borderC);
        drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
    }*/
    
    public static void drawBorderedRect(int x, int y, int x2, int y2, float l1, int col1, int col2)
    {
        drawRect(x, y, x2, y2, col2);

        float f = (float) (col1 >> 24 & 0xFF) / 255F;
        float f1 = (float) (col1 >> 16 & 0xFF) / 255F;
        float f2 = (float) (col1 >> 8 & 0xFF) / 255F;
        float f3 = (float) (col1 & 0xFF) / 255F;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

        GL11.glPushMatrix();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    
    public static void drawRect(float g, float h, float i, float j, int col1)
    {
        float f = (float) (col1 >> 24 & 0xFF) / 255F;
        float f1 = (float) (col1 >> 16 & 0xFF) / 255F;
        float f2 = (float) (col1 >> 8 & 0xFF) / 255F;
        float f3 = (float) (col1 & 0xFF) / 255F;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

        GL11.glPushMatrix();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(i, h);
        GL11.glVertex2d(g, h);
        GL11.glVertex2d(g, j);
        GL11.glVertex2d(i, j);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
    
    public static void drawCircle(float cx, float cy, float r, int numSegments) {
        float theta = 2 * 3.1415926F / ((float)numSegments); 
        float c = (float) Math.cos(theta);//precalculate the sine and cosine
        float s = (float) Math.sin(theta);
        float t;

        float x = r;//we start at angle = 0 
        float y = 0; 

        GL11.glBegin(GL11.GL_LINE_LOOP); 
        for(int ii = 0; ii < numSegments; ii++) { 
            
            GL11.glVertex2f(x + cx, y + cy);//output vertex 
            
            //apply the rotation matrix
            t = x;
            x = c * x - s * y;
            y = s * t + c * y;
        } 
        GL11.glEnd(); 
    }

}
