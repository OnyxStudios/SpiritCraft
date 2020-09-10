#version 120

uniform sampler2D textureSampler;
uniform float time;

void main() {
    float slowTime = time / 25;
    vec3 uv = vec3((gl_TexCoord[0].xy * 0.5), 0.0);
    vec3 sp = vec3(sin(uv.x + slowTime), cos(uv.y + slowTime), sin(uv.x + slowTime));
    for (float i = 1.0; i < 10.0; i++) {
        uv.x += 0.1 / i * sin(i * uv.y * uv.x * 1.0);
        uv.y += 0.1 / i * cos(i * (uv.x * uv.x - uv.y * uv.y));
    }

    vec3 flow = distance(uv, sp) - vec3(sin(sp.y + slowTime), cos(sp.y + slowTime), sin(sp.x - slowTime));
    vec3 colA = vec3(0.4, 0.9, 0.4);
    vec3 colB = vec3(0.4, 0.4, 0.9);
    vec3 col = mix(colA * flow, colB * flow, 0.5);

    gl_FragColor = vec4(col, 0.6) * texture2D(textureSampler, gl_TexCoord[0].xy);
}