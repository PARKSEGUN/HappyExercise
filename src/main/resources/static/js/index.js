let index = {
    init: function (){
        let elementsByName = document.getElementsByName('price');
        for (let elementsByNameElement of elementsByName) {
            elementsByNameElement.innerText += "원";
        }
    }
}
index.init();