#version 150

#moj_import <fog.glsl>

in vec3 Position;
in vec2 UV0;
in vec2 UV2;
in vec4 Color;

uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform int FogShape;

out float vertexDistance;
out vec2 texCoord0;
out vec2 texCoord2;
out vec4 vertexColor;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    vertexDistance = fog_distance(ModelViewMat, Position, FogShape);
    texCoord0 = UV0;
    texCoord2 = UV2;
    vertexColor = Color;
}
