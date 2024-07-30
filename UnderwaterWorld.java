package sem4stillnetbeans;

import javax.swing.*;
import java.awt.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

public class UnderwaterWorld extends JFrame implements GLEventListener {
    
    private float waveAmplitude = 0.1f; // Amplitude of the wave
    private float waveFrequency = 2.0f; // Frequency of the wave
    private float wavePhase = 0.0f; // Phase of the wave
    private float xMoveWhale = -0.6f;
    

    // Function to calculate the height of the ocean floor based on a sine wave
    private float calculateOceanHeight(float x) {
        return (float) Math.sin(x * waveFrequency + wavePhase) * waveAmplitude;
    }

    public UnderwaterWorld() {
        setTitle("Underwater Background");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);
        canvas.setPreferredSize(new Dimension(800, 600));
        canvas.addGLEventListener(this);
        
        getContentPane().add(canvas);
        
        Animator animator = new Animator(canvas);
        animator.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UnderwaterWorld mainWindow = new UnderwaterWorld();
            mainWindow.pack();
            mainWindow.setLocationRelativeTo(null);
            mainWindow.setVisible(true);
        });
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.7451f, 0.8863f, 0.9804f, 1.0f); // Light blue color for the background

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        renderHill(gl, 0.5f, 0.5f, 0.8f, 0.3f, 0.24f, 0.08f);
        // Render a simple quad to represent the ocean floor
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.004f, 0.2039f, 0.4039f); // Dark blue color for the ocean floor
        for (float x = -1.0f; x < 1.0f; x += 0.1f) {
            float oceanHeight = calculateOceanHeight(x);
            gl.glVertex2f(x, -1.0f + oceanHeight);
            gl.glVertex2f(x + 0.1f, -1.0f + oceanHeight);
            gl.glVertex2f(x + 0.1f, 0.5f + oceanHeight);
            gl.glVertex2f(x, 0.5f + oceanHeight);
        }
        gl.glEnd();
        wavePhase += 0.01f;
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.2f, 0.4f, 0.6f); // Dark blue color for the ocean floor
        gl.glVertex2f(-1.0f, -1.0f);
        gl.glVertex2f(1.0f, -1.0f);
        gl.glColor3f(0.0f, 0.2f, 0.4f); // Light blue color for the ocean floor gradient
        gl.glVertex2f(1.0f, 0.5f);
        gl.glVertex2f(-1.0f, 0.5f);
        gl.glEnd();
        
        renderStone(gl, -0.7f, -0.93f, 0.07f);
        renderStone(gl, -0.6f, -0.93f, 0.05f);
        renderStone(gl, -0.3f, -0.93f, 0.06f);
        renderStone(gl, -0.1f, -0.94f, 0.05f);
        renderStone(gl, 0.05f, -0.93f, 0.1f);
        renderStone(gl, 0.3f, -0.93f, 0.06f);
        renderStone(gl, 0.6f, -0.93f, 0.08f);
        renderStone(gl, 0.8f, -0.93f, 0.1f);
        renderHill2(gl, 0.0f, 0.5f, 0.5f, 0.2f, 0.2f, 0.08f);
        
        renderCloud(gl, 0.2f, 0.8f, 0.3f);
        renderSun(gl, 0.8f, 0.8f, 0.1f, 0.1f, 4.0f, 8);
        renderCloud(gl, -0.5f, 0.8f, 0.3f);
        renderBoat(gl, -0.95f, 0.4f, 0.5f, 0.2f);
        renderFish(gl, 0.5f, -0.5f, 0.1f);
        renderFish(gl, 0.55f, -0.6f, 0.1f);
        renderFish(gl, 0.7f, -0.5f, 0.1f);
        renderSubmarine(gl, -0.4f, -0.4f, 0.3f, 0.2f, 0.1f);
        xMoveWhale += 0.0003f;
        float animationLimitWhale = 0.8f; 
            if (xMoveWhale >= animationLimitWhale) {
                xMoveWhale = -0.6f; 
    }
        renderWhale(gl, xMoveWhale, 0f, 0.5f, 0.025f, 0.4f, 0.3f);
        
        renderSeagrass(gl, -0.9f, -1f, 0.1f, 0.3f); 
        renderSeagrass(gl, -0.8f, -1f, 0.1f, 0.35f);
        renderSeagrass(gl, -0.5f, -1f, 0.1f, 0.3f);
        renderSeagrass(gl, -0.4f, -1f, 0.1f, 0.35f);
        renderSeagrass(gl, -0.1f, -1f, 0.1f, 0.3f);
        renderSeagrass(gl, 0.1f, -1f, 0.1f, 0.35f);
        renderSeagrass(gl, 0.2f, -1f, 0.1f, 0.3f);
        renderSeagrass(gl, 0.5f, -1f, 0.1f, 0.35f);
        renderSeagrass(gl, 0.7f, -1f, 0.1f, 0.3f);
        renderSeagrass(gl, 0.9f, -1f, 0.1f, 0.3f);
        
        renderPrawn(gl, -0.3f, 0.21f, 0.1f);
        renderPrawn(gl, -0.2f, 0.3f, 0.1f);
        renderPrawn(gl, -0.5f, 0.3f, 0.1f);
        renderBird(gl, -0.1f, 0.7f, 0.1f, 0.05f, 0.03f,0.2f, 0.1f);
        renderBird(gl, 0.1f, 0.8f, 0.1f, 0.05f, 0.03f,0.2f, 0.1f);
        
        renderCoral(gl, -0.7f, -1f, 0.15f);
        renderCoral(gl, -0.5f, -1f, 0.1f);
        renderCoral(gl, 0.3f, -1f, 0.2f);
        renderCoral(gl, -0.5f, -1f, 0.15f);
        renderCoral(gl, -0.6f, -1f, 0.1f);
        renderCoral(gl, 0f, -1f, 0.2f);
        renderCoral(gl, 0.2f, -1f, 0.15f);
        renderCoral(gl, 0.4f, -1f, 0.1f);
        renderCoral(gl, 0.8f, -1f, 0.2f);
        
    }

    private float angleOffset = 0.0f;

    private void renderSun(GL2 gl, float x, float y, float radius, float rayLength, float rayWidth, int numRays) {
        gl.glColor3f(1.0f, 1.0f, 0.0f); 

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x, y); 
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + radius * (float) Math.cos(angle);
            float yPos = y + radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();

        angleOffset += 1.0f; 

        float angleIncrement = 360.0f / numRays;
        for (int i = 0; i < numRays; i++) {
            float angle = (float) Math.toRadians(angleIncrement * i + angleOffset);
            float xEnd = x + (radius + rayLength) * (float) Math.cos(angle);
            float yEnd = y + (radius + rayLength) * (float) Math.sin(angle);
            gl.glLineWidth(rayWidth); 
            gl.glBegin(GL2.GL_LINES);
            gl.glVertex2f(x, y); 
            gl.glVertex2f(xEnd, yEnd);
            gl.glEnd();
        }
    }

    private void renderCloud(GL2 gl, float x, float y, float radius) {
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x, y + 0.3f * radius); 
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + 0.3f * radius * (float) Math.cos(angle);
            float yPos = y + 0.3f * radius + 0.2f * radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();
        
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x - 0.5f * radius, y);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x - 0.2f * radius + 0.4f * radius * (float) Math.cos(angle);
            float yPos = y - 0.2f * radius + 0.3f * radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x + 0.5f * radius, y);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + 0.5f * radius - 0.4f * radius * (float) Math.cos(angle);
            float yPos = y - 0.2f * radius + 0.3f * radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x - 0.3f * radius, y); 
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x - 0.2f * radius + 0.3f * radius * (float) Math.cos(angle);
            float yPos = y + 0.3f * radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x + 0.3f * radius, y); 
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + 0.4f * radius - 0.3f * radius * (float) Math.cos(angle);
            float yPos = y + 0.4f * radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();
    }
    
    private void renderBoat(GL2 gl, float x, float y, float width, float height) {
        gl.glColor3f(0.545f, 0.271f, 0.075f); 
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(x + width, y + height); 
        gl.glVertex2f(x, y + height); 
        gl.glVertex2f(x + 0.2f * width, y);
        gl.glVertex2f(x + 0.8f * width, y); 
        gl.glEnd();

        gl.glColor3f(0.545f, 0.271f, 0.075f); 
        gl.glLineWidth(3.0f); 
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x + 0.5f * width, y + height); 
        gl.glVertex2f(x + 0.5f * width, y + (2*height));
        gl.glEnd();

        gl.glColor3f(0.6196f, 0.2039f, 0.9215f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + 0.5f * width, y + (2*height)); 
        gl.glVertex2f(x + 0.5f * width, y + height + 0.05f); 
        gl.glVertex2f(x + 0.8f * width, y + height + 0.05f);
        gl.glEnd();
    }
    
    private void renderFish(GL2 gl, float x, float y, float size) {
        gl.glColor3f(0.749f, 0.7882f, 0.7529f);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(x - size * 0.5f, y); 
        gl.glVertex2f(x - size * 0.2f, y + size * 0.3f); 
        gl.glVertex2f(x + size * 0.5f, y + size * 0.2f); 
        gl.glVertex2f(x + size * 0.7f, y);
        gl.glVertex2f(x + size * 0.5f, y - size * 0.2f); 
        gl.glVertex2f(x - size * 0.2f, y - size * 0.3f);
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x - size * 0.5f, y);
        gl.glVertex2f(x - size, y + size * 0.3f);
        gl.glVertex2f(x - size, y - size * 0.3f);
        gl.glEnd();
    }
    
    private void renderSubmarine(GL2 gl, float x, float y, float width, float height, float radius) {
        gl.glColor3f(0.2f, 0.2f, 0.2f);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + 0.5f * width * (float) Math.cos(angle);
            float yPos = y + 0.5f * height * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();

        gl.glColor3f(0.6f, 0.6f, 0.6f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x + width * 0.5f - 0.12f, y + height - 0.1f); 
        gl.glVertex2f(x + width * 0.5f -0.12f, y + height * 1.2f - 0.1f); 
        gl.glEnd();

        gl.glColor3f(0.7568f, 0.8392f, 0.8784f);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f((x + 0.5f * radius)-0.1f, y + 0.04f); 
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + 0.5f * radius - 0.4f * radius * (float) Math.cos(angle);
            float yPos = y - 0.2f * radius + 0.3f * radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos-0.1f, yPos + 0.04f);
        }
        gl.glEnd();

        gl.glColor3f(0.6f, 0.6f, 0.6f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + width * 0.2f - 0.05f, y - height * 0.1f -0.07f);
        gl.glVertex2f(x + width * 0.3f - 0.05f, y - height * 0.3f -0.07f);
        gl.glVertex2f(x + width * 0.4f - 0.05f, y - height * 0.1f -0.07f);
        gl.glEnd();
        
        gl.glColor3f(0.6f, 0.6f, 0.6f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + width * 0.3f + 0.05f, y - height * 0.1f -0.08f + 0.15f);
        gl.glVertex2f(x + width * 0.3f + 0.05f, y - height * 0.3f -0.04f + 0.05f);
        gl.glVertex2f(x + width * 0.4f + 0.05f, y - height * 0.1f -0.04f + 0.05f);
        gl.glEnd();
    }

    private void renderStone(GL2 gl, float x, float y, float radius) {
        float[] stoneColor = {0.49f, 0.4745f, 0.4431f};
        float[] borderColor = {0.2f, 0.2f, 0.2f};

        gl.glColor3fv(borderColor, 0);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x, y); 
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + (radius + 0.02f) * (float) Math.cos(angle);
            float yPos = y + (radius + 0.02f) * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();

       
        gl.glColor3fv(stoneColor, 0);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x, y);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + radius * (float) Math.cos(angle);
            float yPos = y + radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();
    }
    
    private void renderWhale(GL2 gl, float x, float y, float size, float radius, float width, float height) {
        x += xMoveWhale;
        float[] bodyColor = {0.3529f, 0.4941f, 0.5725f}; 
        float[] eyeColor = {1.0f, 1.0f, 1.0f}; 

        gl.glColor3fv(bodyColor, 0);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + 0.5f * size * (float) Math.cos(angle);
            float yPos = y + 0.2f * size * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();

        
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x - 0.5f * size + 0.1f, y); 
        gl.glVertex2f(x - 0.8f * size, y + 0.3f * size - 0.1f);
        gl.glVertex2f(x - 0.8f * size, y - 0.3f * size + 0.1f); 
        gl.glEnd();

        gl.glColor3f(0f, 0f, 0f);
        gl.glPointSize(10.0f); 
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x + 0.3f * size - 0.2f, y + 0.1f * size+0.03f);
        gl.glEnd();

        gl.glColor3f(0f, 0f, 0f); 
        for (int i = 0; i < 1; i++) { 
            gl.glBegin(GL2.GL_LINE_LOOP);
            float xPos = x + 0.3f * size;
            float yPos = y + (i == 0 ? 0.1f * size : 0.2f * size) - 0.05f;
            for (int j = 0; j <= 360; j += 10) {
                float angle = (float) Math.toRadians(j);
                float xPoint = xPos + 0.02f * size * (float) Math.cos(angle);
                float yPoint = yPos + 0.02f * size * (float) Math.sin(angle);
                gl.glVertex2f(xPoint-0.2f, yPoint+0.08f);
            }
            gl.glEnd();
        }
        
        gl.glColor3fv(eyeColor, 0);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x+0.2f, y+0.02f);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + radius * (float) Math.cos(angle);
            float yPos = y + radius * (float) Math.sin(angle);
            gl.glVertex2f(xPos+0.2f, yPos+0.02f);
        }
        gl.glEnd();
        
        gl.glColor3f(0f, 0f, 0f); 
        gl.glPointSize(10.0f); 
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x + 0.3f * size, y + 0.1f * size - 0.05f);
        gl.glEnd();
        
        gl.glColor3f(0.5882f, 0.7215f, 0.7882f);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + 0.4f * size * (float) Math.cos(angle);
            float yPos = y - 0.1f * size * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos-0.07f);
        }
        gl.glEnd();
        
        gl.glColor3f(0.3529f, 0.4941f, 0.5725f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + width * 0.2f - 0.1f, y - height * 0.1f -0.1f);
        gl.glVertex2f(x + width * 0.3f - 0.1f, y - height * 0.3f -0.1f);
        gl.glVertex2f(x + width * 0.4f - 0.1f, y - height * 0.1f -0.1f); 
        gl.glEnd();
        
        gl.glColor3f(0.3529f, 0.4941f, 0.5725f); 
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + width * 0.2f - 0.1f, y - height * 0.1f+0.15f); 
        gl.glVertex2f(x + width * 0.3f - 0.1f, y - height * 0.3f+0.3f); 
        gl.glVertex2f(x + width * 0.4f - 0.1f, y - height * 0.1f+0.15f);
        gl.glEnd();
        
    }

    private void renderSeagrass(GL2 gl, float x, float y, float width, float height) {
        gl.glColor3f(0.0f, 0.5f, 0.0f);

        gl.glLineWidth(5.0f); 
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x, y); 
        gl.glVertex2f(x, y + height);
        gl.glEnd();

        gl.glColor3f(0.733f, 0.9294f, 0.733f);
        gl.glLineWidth(3.0f); 
            for (int i = 0; i < 5; i++) {
                gl.glBegin(GL2.GL_LINES);
                float bladeX = x + (float) Math.random() * width - width / 2;
                float bladeY = y + height - i * height / 5 - 0.05f; 

                float bladeMoveX = (float) Math.random() * width / 20 - width / 25;
                float bladeMoveY = (float) Math.random() * height / 16;

                gl.glVertex2f(bladeX, bladeY);
                gl.glVertex2f(bladeX + bladeMoveX, bladeY + bladeMoveY); 
                gl.glEnd();
            }
        }
    
    private void renderPrawn(GL2 gl, float x, float y, float size) {
        float[] bodyColor = {0.988f, 0.702f, 0.639f}; 
        float[] finColor = {0.992f, 0.882f, 0.643f}; 
        float[] eyeColor = {0f, 0f, 0f}; 

        gl.glColor3fv(bodyColor, 0);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + 0.5f * size * (float) Math.cos(angle);
            float yPos = y + 0.2f * size * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();

        // Draw the head of the prawn
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + 0.5f * size, y + 0.2f * size); 
        gl.glVertex2f(x + 0.7f * size, y);
        gl.glVertex2f(x + 0.3f * size, y);
        gl.glEnd();

        // Draw the tail of the prawn
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x - 0.5f * size, y); 
        gl.glVertex2f(x - 0.7f * size, y + 0.3f * size);
        gl.glVertex2f(x - 0.7f * size, y - 0.3f * size); 
        gl.glEnd();

        // Draw the fins of the prawn
        gl.glColor3fv(finColor, 0);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + 0.5f * size, y + 0.1f * size); 
        gl.glVertex2f(x + 0.7f * size, y + 0.3f * size); 
        gl.glVertex2f(x + 0.5f * size, y - 0.1f * size); 
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + 0.5f * size, y + 0.1f * size);
        gl.glVertex2f(x + 0.7f * size, y - 0.3f * size); 
        gl.glVertex2f(x + 0.5f * size, y - 0.1f * size); 
        gl.glEnd();

        gl.glColor3fv(eyeColor, 0);
        gl.glPointSize(5.0f); 
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x + 0.6f * size, y + 0.1f * size);
        gl.glVertex2f(x + 0.4f * size, y + 0.1f * size);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    private void renderBird(GL2 gl, float x, float y, float size, float radius, float radius2, float width, float height){
        gl.glColor3f(0.9804f, 0.8392f, 0.5764f);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x, y); 
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + (radius + 0.02f) * (float) Math.cos(angle);
            float yPos = y + (radius + 0.02f) * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos);
        }
        gl.glEnd();
        
        gl.glColor3f(0.9294f, 0.9176f, 0.733f);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(x, y+0.07f);
        for (int i = 0; i <= 360; i += 10) {
            float angle = (float) Math.toRadians(i);
            float xPos = x + (radius2 + 0.02f) * (float) Math.cos(angle);
            float yPos = y + (radius2 + 0.02f) * (float) Math.sin(angle);
            gl.glVertex2f(xPos, yPos+0.09f);
        }
        gl.glEnd();
        gl.glColor3f(0.8392f, 0.7176f, 0.4863f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x - 0.5f * size + 0.145f, y+0.01f); 
        gl.glVertex2f(x - 0.8f * size+ 0.145f, y + 0.2f * size+0.01f); 
        gl.glVertex2f(x - 0.8f * size+ 0.145f, y - 0.2f * size+0.01f); 
        gl.glVertex2f(x + 0.5f * size-0.145f, y+0.01f);
        gl.glVertex2f(x + 0.8f * size-0.145f, y + 0.2f * size+0.01f);
        gl.glVertex2f(x + 0.8f * size-0.145f, y - 0.2f * size+0.01f); 
        gl.glEnd();
        
        gl.glColor3f(0.3529f, 0.4941f, 0.5725f); 
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x + width * 0.2f-0.06f, y - height * 0.1f +0.08f); 
        gl.glVertex2f(x + width * 0.3f-0.06f, y - height * 0.3f +0.08f); 
        gl.glVertex2f(x + width * 0.4f-0.06f, y - height * 0.1f +0.08f);
        gl.glEnd();
        
        gl.glColor3f(0f, 0f, 0f);
        gl.glPointSize(5.0f); 
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x + 0.6f * size - 0.045f, y + 0.1f * size+0.08f);
        gl.glVertex2f(x + 0.3f * size- 0.045f, y + 0.1f * size+0.08f);
        gl.glEnd();
    }
    
    private void renderHill(GL2 gl, float x, float y, float baseWidth, float height, float baseWidth1, float height1) {
        gl.glColor3f(0.1412f, 0.6392f, 0.2078f);

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x - baseWidth / 2.0f, y); 
        gl.glVertex2f(x + baseWidth / 2.0f, y);
        gl.glVertex2f(x, y + height);
        gl.glEnd();
        
        gl.glColor3f(0.8105f, 0.8353f, 0.1725f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x - baseWidth1 / 2.0f, y+height-height1);
        gl.glVertex2f(x + baseWidth1 / 2.0f, y+height-height1);
        gl.glVertex2f(x, y + height); 
        gl.glEnd();
    }
    
    private void renderHill2(GL2 gl, float x, float y, float baseWidth, float height, float baseWidth1, float height1) {
        gl.glColor3f(0.1137f, 0.4784f, 0.1647f); 

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x - baseWidth / 2.0f, y); 
        gl.glVertex2f(x + baseWidth / 2.0f, y);
        gl.glVertex2f(x, y + height);
        gl.glEnd();
        
        gl.glColor3f(0.8105f, 0.8353f, 0.1725f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(x - baseWidth1 / 2.0f, y+height-height1); 
        gl.glVertex2f(x + baseWidth1 / 2.0f, y+height-height1);
        gl.glVertex2f(x, y+height); 
        gl.glEnd();
    }

    private void renderCoral(GL2 gl, float x, float y, float size) {
        gl.glColor3f(1.0f, 0.5f, 0.5f);
        gl.glLineWidth(3.0f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x, y);
        gl.glVertex2f(x, y + size); 
        gl.glEnd();

        gl.glColor3f(1.0f, 0.75f, 0.75f); 
        for (int i = 0; i < 3; i++) {
            float branchX = x + (float) Math.random() * size / 2 - size / 4; 
            float branchY = y + size - i * size / 4 - 0.05f; 
            float branchLength = size / 3 + (float) Math.random() * size / 4; 
            gl.glBegin(GL2.GL_LINES);
            gl.glVertex2f(x, y);
            gl.glVertex2f(branchX, branchY + branchLength);
            gl.glEnd();
        }
    }
 

}

