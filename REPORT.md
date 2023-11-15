# CS3035 – Course Project Description

## Description of Your Project

Below, provide a short description of what your project is, how it works, and why you selected this as your project.


## Requirements
https://web.microsoftstream.com/video/74d9fa0a-2374-44d8-8ec8-60c1a649666a?list=studio

- How/What different views did you provide for some aspect of your model?
I provided two views, drawingView and createView. createView allows users to chose settings of their canvas and pixel
size, along with showing them a provides of the canvas and one pixel. There is also a help section in the menu to help
the user understand the controls. The drawingView is created when the create button is pushed in createView. DrawingView
 hosts the main functionalities of my application. It sets up the canvas the user wanted with a grid to track and
 interactions with the canvas. Draw is one of the three tools I have set up for drawingView allowing the user to select
 colors, add them to the canvas, and even choose colors from a recent colors list that hold up to 50 of the most recent
 colors. Erase allows the user to return pixels to their original state before any drawing occurred. The grid button allows
 users to preview their image before a save without the grid in the way, although it is recommended to keep visible to
 help users know where pixels will fall when drawing. This View's menu provides three options, make a new project, save
 the current project as a png, and once again another help popup explaining details of the drawingView.

- What custom widget did you create in your application?
The custom widget I created was a color wheel, it allows the user to select a color off a large array of colors. This was constructed
by making 360 lines on a canvas, that all fade from their individual color to white, in the middle I have created a black circle as
I figured it may be a commonly used color in the application.there is a circle that also follows the cursor so no matter what
color the cursor is on it is clear what is selected.

- What are the different domain objects that can be created/edited in 
your application?
I made a grid, pixels, and a color list.
Pixels hold colors, positions, and sizes used to paint the canvas.

The grid is used as a guide on top of the canvas, it holds a group of pixels that cover the whole canvas.
It also is in charge of ensuring pixels are colored and erased properly, by ensuring the proper pixels are effected.

Color list is used for the recent colors list, it holds a list or rectangles and colors that have been recently used. it
 also updates appropriately when the canvas is drawn on, and when it reaches the color limit.

- What parts of the application/project did you find particularly challenging? 
And, what would you have liked to improve?
I found my initial widget challenging, when the professor said "Hey that is going to be hard" I decided hey maybe not
the best idea. So I was going to make a zoom widget but the users can decide their pixel size anyways it just may be
hard to get very detailed drawings, still possible technically though. Anyways I retracted back to my first idea I
thought was going to be too hard to figure out. I made a color wheel, it went better than anticipated.

I also initially had difficulty figuring out how to swap between views, but I figured it out on my own so that is good.

Another issue was spacing who knew wanting to center things in a side drawer would be so tedious.

As for improvements, I really like my application to be honest it works pretty decent.
There is things I want to add though, and I just might because I find this app really fun to play with. I would like to
add a eyedropper tool to pick colors from the canvas, a brightness tool so you can darken colors.

- Any  other comments on the course project?
I really enjoyed this project, it really set in how much I enjoy working with front end design. Now when applying for
PEP positions I will keep this in mind. I am also very happy that I have finally gotten to do a final project on
something I actually cared about, it made the application a lot easier to work on. This also was a nice confidence boost
as I am pretty happy with my final project, and I actually started it later than intended due to a cold and the mental
effects of isolation. Yet I have it finished early as of right now I have my final product completely working and its a
week before the project is due. I think the fun I had with this project is hard to miss, I even used my project to create
my own logo just because I could. This report may feel longer than anticipated but I could not fit a description of all I
have put into this application. I suggest you try it yourself.






