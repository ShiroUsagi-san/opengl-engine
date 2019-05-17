#version 330 core

 in vec4 extColor;
 out vec4 fragColor;
 uniform vec4 inColor;

 void main()
 {
     fragColor = vec4(inColor);
 }