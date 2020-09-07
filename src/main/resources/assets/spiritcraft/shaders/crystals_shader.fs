#version 120

uniform sampler2D textureSampler;
uniform float time;
uniform vec3 crystalColor;

void main() {
    vec4 sum = vec4(0);
    vec2 texcoord = gl_TexCoord[0].xy;
    vec4 textureColor = texture2D(textureSampler, texcoord);
    float brightness = sin(time / 20.0) * 0.5 + 0.5;
    vec4 finalColor;

    for(int i = -4 ; i < 4; i++)
        for(int j = -3; j < 3; j++)
            sum += texture2D(textureSampler, texcoord + vec2(j, i) * 0.004) * 0.25;


    if(textureColor.r < 0.3) {
        finalColor = sum * sum * 0.012 + textureColor;
    } else {
        if(textureColor.r < 0.5)
            finalColor = sum * sum * 0.009 * brightness + textureColor;
        else finalColor = sum * sum * 0.0075 * brightness + textureColor;
    }

    gl_FragColor = finalColor * vec4(crystalColor, 1);
}