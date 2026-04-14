const amountCounter = document.querySelector('#amount-input')

let choice = {bottom: null, bottomPrice: 0, top: null, topPrice: 0}


function choose(type, el) {
    let name = el.dataset.name
    let price = parseInt(el.dataset.price)
    document.querySelectorAll('#bund-grid, .option-grid').forEach(b => b.classList.remove('selected'))
    el.classList.add('selected')
    el.querySelector('input[type=radio]').checked = true
    choice[type] = name
    choice[type + 'Price'] = price
    update()
}

function update() {
    document.getElementById('sum-bottom').textContent = choice.bottom
    document.getElementById('sum-top').textContent = choice.top
    document.getElementById('sum-amount').textContent = amountCounter.value.toString()
    document.getElementById('amount-val').value = amountCounter.value
}

function countMinus() {
    amountCounter.value = Math.max(1, parseInt(amountCounter.value) - 1)
    update()
}

function countPlus() {
    amountCounter.value = parseInt(amountCounter.value) + 1
    update()
}