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