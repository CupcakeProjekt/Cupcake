let orders = []

let choice = {
    bottom: null,
    bottomPrice: 0,
    top: null,
    topPrice: 0
}

function update() {
    const bottomSelect = document.getElementById('bottom-select')
    const topSelect = document.getElementById('top-select')
    const amountSelect = document.getElementById('amount-select')
    const button = document.getElementById('bestil-btn')

    const bottomOption = bottomSelect.options[bottomSelect.selectedIndex]
    const topOption = topSelect.options[topSelect.selectedIndex]

    if (bottomSelect.value !== '') {
        choice.bottom = bottomOption.dataset.name
        choice.bottomPrice = parseInt(bottomOption.dataset.price)
    } else {
        choice.bottom = null
        choice.bottomPrice = 0
    }

    if (topSelect.value !== '') {
        choice.top = topOption.dataset.name
        choice.topPrice = parseInt(topOption.dataset.price)
    } else {
        choice.top = null
        choice.topPrice = 0
    }

    const amount = amountSelect.value !== '' ? parseInt(amountSelect.value) : 0

    button.disabled = !(choice.bottom && choice.top && amount > 0)
}

function addToOrder() {
    const amountSelect = document.getElementById('amount-select')
    const amount = amountSelect.value !== '' ? parseInt(amountSelect.value) : 0

    const orderItem = {
        bottom: choice.bottom,
        bottomPrice: choice.bottomPrice,
        top: choice.top,
        topPrice: choice.topPrice,
        amount: amount
    }

    orders.push(orderItem)

    console.log("orders", orders)

    sendOrder()

    document.getElementById('bottom-select').value = ''
    document.getElementById('top-select').value = ''
    document.getElementById('amount-select').value = ''

    choice.bottom = null
    choice.bottomPrice = 0
    choice.top = null
    choice.topPrice = 0

    update()
}

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('bestil-btn').addEventListener('click', addToOrder)
})

function sendOrder() {
    console.log("sending to backend", orders)

    fetch('/api/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orders)
    })
        .then(async res => {
            const text = await res.text()

            if (!res.ok) {
                console.log("Backend svarede med fejl:", res.status, text)
                return
            }

            try {
                const data = JSON.parse(text)
                console.log("Success:", data)
            } catch {
                console.log("Success, men svaret var ikke JSON:", text)
            }
        })
        .catch(err => {
            console.log("Error:", err)
        })
}