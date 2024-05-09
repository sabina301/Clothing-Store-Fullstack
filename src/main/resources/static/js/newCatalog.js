let catalogItem = [
    { image: "img/cl1.jpg", name: "Куртка", price: 5100 },
    { image: "img/cl2.jpg", name: "Кофта", price: 2000 },
    { image: "img/cl3.jpg", name: "Футболка", price: 900 },
    { image: "img/cl4.jpg", name: "Свитшот", price: 1100 },
];

let catalogItemNow = [];

function displayCatalogItems(items) {
    let catalogContainer = document.getElementById("new-container");
    catalogContainer.innerHTML = "";
    items.forEach((item) => {
        let productContainer = document.createElement("div");
        productContainer.classList.add("product-container");

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
        productContainer.addEventListener("click", function () {
            window.location.href = "product.html";
        });
    });
    catalogItemNow = items;
}
displayCatalogItems(catalogItem);