#version 330 core

 in vec3 extColor;
 out vec4 fragColor;

 void main()
 {
     fragColor = vec4(extColor, 1.0);
 }