
let url = '/category/showproducts/1';

fetch(url, {
    method: 'GET'
})
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Успешный ответ от сервера:', data);
        displayCatalogItems(data);
    })
    .catch(error => {
        console.error('Ошибка при выполнении запроса:', error);
    });

let catalogItemNow = [];

//Сортировка
document.querySelector("button").addEventListener("click", function () {
    let minPrice = parseInt(document.querySelector("input:nth-of-type(1)").value);
    let maxPrice = parseInt(document.querySelector("input:nth-of-type(2)").value);
    catalogItemNow = catalogItem;
    let filteredItems;
    if (minPrice < maxPrice) {
        filteredItems = catalogItemNow.filter(
            (item) => item.price >= minPrice && item.price <= maxPrice
        );
        displayCatalogItems(filteredItems);
    }
});

// Обработчик события на кнопку "По убыванию"
document.querySelectorAll("button")[1].addEventListener("click", function () {
    catalogItemNow.sort((a, b) => b.price - a.price);
    displayCatalogItems(catalogItemNow);
});

// Обработчик события на кнопку "По возрастанию"
document.querySelectorAll("button")[2].addEventListener("click", function () {
    catalogItemNow.sort((a, b) => a.price - b.price);
    displayCatalogItems(catalogItemNow);
});

function displayCatalogItems(items) {
    let catalogContainer = document.querySelector(".container");
    catalogContainer.innerHTML = "";
    items.forEach((item) => {
        let productContainer = document.createElement("div");
        productContainer.classList.add("product-container");

        let itemName = document.createElement("div");
        itemName.textContent = item.productName;
        itemName.classList.add("caption");

        let itemPrice = document.createElement("div");
        itemPrice.textContent = item.productPrice + "р";
        itemPrice.classList.add("caption-price");

        let itemImg = document.createElement("img");
        itemImg.src = "/img/cl.jpg";
        itemImg.classList.add("product-img");
        productContainer.appendChild(itemImg);
        productContainer.appendChild(itemName);
        productContainer.appendChild(itemPrice);
        catalogContainer.appendChild(productContainer);
        productContainer.addEventListener("click", function () {
            window.location.href = "/product/"+item.id;
        });
    });
    catalogItemNow = items;
}
