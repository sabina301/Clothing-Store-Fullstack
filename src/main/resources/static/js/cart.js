

const jwt = getCookie('jwt'); // Замените на имя вашей куки с JWT токеном

fetch(`/cart/show`, {
    method: 'GET',
    headers: {
        Authorization: "Bearer " + jwt,
        Cookie: document.cookie,
    },
})
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Успешный ответ от сервера:', data);
        displayCartItems(data)
    })
    .catch(error => {
        console.error('Ошибка при выполнении запроса:', error);
    });

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}



let summa = document.createElement("p");
summa.classList.add("summa");

function displayCartItems(cartItem) {
    let cartContainer = document.querySelector(".cont");
    let totalSumm = 0;
    cartItem.forEach((item) => {
        let productContainer = document.createElement("div");
        productContainer.classList.add("product-container");

        //INSCRIPTION
        let inscriptions = document.createElement("div");
        inscriptions.classList.add("inscriptions");

        let itemName = document.createElement("p");
        itemName.textContent = item.productName;
        itemName.classList.add("inscriptions-text");

        let itemPrice = document.createElement("p");
        itemPrice.textContent = item.productPrice + "р";
        itemPrice.classList.add("inscriptions-text");
        totalSumm += item.productPrice;

        //CROSS
        let crossContainer = document.createElement("div");
        crossContainer.classList.add("cross-container");

        let background2 = document.createElement("div");
        background2.classList.add("background");

        let cross = document.createElement("img");
        cross.classList.add("cross");
        cross.src = "/img/cross.png";

        crossContainer.addEventListener("click", function () {
            let index = Array.from(this.parentNode.parentNode.children).indexOf(
                this.parentNode
            );
            cartItem.splice(index, 1);
            this.parentNode.remove();
            totalSumm -= item.productPrice * quantity;
            summa.textContent = "Итоговая сумма: " + totalSumm + "р";
        });

        //IMG
        let itemImg = document.createElement("img");
        itemImg.src = "/img/cl.jpg";
        itemImg.classList.add("img-for-product");

        //COUNTER
        let counterContainer = document.createElement("div");
        counterContainer.classList.add("counter-container");

        let minusContainer = document.createElement("div");
        minusContainer.classList.add("minus-container");

        let plusContainer = document.createElement("div");
        plusContainer.classList.add("plus-container");

        let minus = document.createElement("img");
        minus.src = "/img/minus.png";
        minus.classList.add("minus");

        let plus = document.createElement("img");
        plus.src = "/img/cross.png";
        plus.classList.add("plus");

        let num = document.createElement("p");
        num.classList.add("num");
        num.textContent = 1;

        let quantity = 1;

        minusContainer.addEventListener("click", function () {
            if (quantity > 1) {
                quantity--;
                num.textContent = quantity;
                totalSumm -= item.productPrice;
                summa.textContent = "Итоговая сумма: " + totalSumm + "р";
            }
            if (quantity == 1) {
                minusContainer.style.backgroundColor = "gray";
            } else {
                minusContainer.style.backgroundColor = "white";
            }
        });

        plusContainer.addEventListener("click", function () {
            quantity++;
            totalSumm += item.productPrice;
            summa.textContent = "Итоговая сумма: " + totalSumm + "р";
            num.textContent = quantity;
            if (quantity == 1) {
                minusContainer.style.backgroundColor = "gray";
            } else {
                minusContainer.style.backgroundColor = "white";
            }
        });

        //__________________________________________________//

        inscriptions.appendChild(itemName);
        inscriptions.appendChild(itemPrice);

        background2.appendChild(cross);
        crossContainer.appendChild(background2);

        plusContainer.appendChild(plus);
        counterContainer.appendChild(num);
        minusContainer.appendChild(minus);

        counterContainer.appendChild(plusContainer);
        counterContainer.appendChild(minusContainer);

        productContainer.appendChild(itemImg);
        productContainer.appendChild(inscriptions);
        productContainer.appendChild(crossContainer);
        productContainer.appendChild(counterContainer);
        cartContainer.appendChild(productContainer);
    });
    //SUMMA
    summa.textContent = "Итоговая сумма: " + totalSumm + "р";
    cartContainer.appendChild(summa);
}



document.getElementById('do-order').addEventListener('click', function() {
    const url = `/order/create`;
    const jwt = getCookie('jwt');
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

