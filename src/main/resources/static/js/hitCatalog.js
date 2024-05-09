let catalogItemHit = [
    { image: "img/cl5.jpg", name: "Куртка", price: 5100 },
    { image: "img/cl6.jpg", name: "Кофта", price: 2000 },
    { image: "img/cl7.jpg", name: "Футболка", price: 900 },
    { image: "img/cl8.jpg", name: "Куртка", price: 2000 },
];

let catalogItemNowHit = [];

function displayCatalogItems(items) {
    let catalogContainer = document.getElementById("hit-container");
    catalogContainer.innerHTML = "";
    items.forEach((item) => {
        let productContainer = document.createElement("div");
        productContainer.classList.add("product-container2");

        let itemName = document.createElement("div");
        itemName.textContent = item.name;
        itemName.classList.add("caption");

        let itemPrice = document.createElement("div");
        itemPrice.textContent = item.price + "р";
        itemPrice.classList.add("caption-price");

        let itemImg = document.createElement("img");
        itemImg.src = item.image;
        itemImg.classList.add("product-img");

        productContainer.appendChild(itemImg);
        productContainer.appendChild(itemName);
        productContainer.appendChild(itemPrice);
        catalogContainer.appendChild(productContainer);
    });
    catalogItemNowHit = items;
}
displayCatalogItems(catalogItemHit);