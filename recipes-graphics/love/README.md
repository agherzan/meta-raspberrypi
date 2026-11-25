# Love Recipe for Yocto

![Love 11.5](https://img.shields.io/badge/Love-11.5-blue.svg)
![Lua](https://img.shields.io/badge/Lua-5.1+-green.svg)

## Add to your image

Add love to your Yocto image by adding it to `IMAGE_INSTALL` in your configuration file:

```
IMAGE_INSTALL:append = " love"
```

## Run the example

```
love /usr/share/love/examples/hello-world.lua
```
