-- main.lua
function love.load()
    font = love.graphics.newFont(24)
end

function love.draw()
    love.graphics.setFont(font)
    love.graphics.print("Hello World", 350, 280)
end