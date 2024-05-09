
const currentUrl = window.location.href;
url = currentUrl+"/get"

fetch(url, {
    method: 'GET',

})
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        displayProduct(data);
    })
    .catch(error => {
        console.error('Ошибка при выполнении запроса:', error);
    });

function displayProduct(product) {
    let container = document.querySelector(".container");
    let priceContainer = document.getElementById("price-container");
    let imgContainer = document.querySelector(".img-container");

    let img = document.createElement("img");
    img.src = "/img/cl.jpg";

    imgContainer.appendChild(img);
    container.appendChild(imgContainer);

    document.getElementById("product").textContent = product.productName;
    document.getElementById("with-sale").textContent = product.productPrice+"руб.";
    document.getElementById("product-description").textContent = product.productDescription;

}

document.getElementById('add-to-cart').addEventListener('click', function() {
    let productIdSplit = currentUrl.split('/')
    let productId = productIdSplit[productIdSplit.length - 1]
    const url = `/cart/addproduct/${productId}`;
    const jwt = getCookie('jwt'); // Замените на имя вашей куки с JWT токеном


    fetch(url, {
        method: "POST",
        headers: {
            Authorization: "Bearer " + jwt,
            Cookie: document.cookie,
        },
    })
        .then((response) => {
            if (response.status === 200) {
                console.log("OK")
            } else {
                console.error("Error", response.status);
            }
        })
        .catch((error) => console.error("Error", error));
});

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
