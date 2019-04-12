#version 330 core

layout (location=0) in vec3 position;

out vec3 extColor;

uniform mat4 projectionMatrix;
uniform mat4 modelMatrix;
uniform vec3 inColor;
void main()
{
    gl_Position = projectionMatrix * modelMatrix  * vec4(position, 1.0);
    extColor = inColor;
}