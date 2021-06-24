# LBC Test

Hey!
This is my attempt at the Leboncoin Test.
The main goal is to display a list of objects in this format

```
{
	"albumId": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952",
    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
}
```

My interpretation of this json is that there are multiple albums to be displayed first, then you should be able to access one specific album and see its pictures.

This is what came out of the one week test, having timeboxed myself to 1h30 hours per day.

[![](https://res.cloudinary.com/marcomontalbano/image/upload/v1624562273/video_to_markdown/images/streamable--4undjt-c05b58ac6eb4c4700831b2b3070cd403.jpg)](https://streamable.com/4undjt "")

# Android App

This application was developed with the clean architecture principles in mind, fully unit-tested, except for the view layer.

## Architecture

<img src="https://puu.sh/HRrVN/784e5f1141.png"  
alt="architecture schema"  
style="float: left; margin-right: 10px;" />


# TODO List

What I could/should have done if I had spent more time on it:

- More loaders, as there is way too much data to load at once
- Benchmark other libraries than picasso
- Spending more time on design. There is absolutely no theming done
- Put the base url in a gradle var
- Add more texts, like the album id below each album on the main screen, and on the detail toolbar title
