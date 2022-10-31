console.log('Hello world');

function clickEvent(id){
    console.log(id);
    switch (id) {
        case "firstContentCard":
            window.open('https://www.google.com/search?q=bamboo+images')
            break;
        case "secondContentCard":
            window.open('https://www.google.com/search?q=flower+images');
            break;
        case "thirdContentCard":
            window.open('https://www.google.com/search?q=bird+images');
            break;
        case "fourthContentCard":
            window.open('https://www.google.com/search?q=ocean+wave+images');         
            break;
    }
}