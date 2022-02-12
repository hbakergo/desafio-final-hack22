const neDB = require('../configurations/database')
const api = {}

api.save = (request, response) => {
    const canonical = request.body
    neDB.insert(canonical, (exception, card) => {
        if(exception) { 
            const sentence = 'Não foi possível salvar Card!'
            console.error(sentence, exception)

            response.status(exception.status | 400)
            response.json({ 'mensagem': sentence })
        }
        console.log('Card salvo com sucesso!', card)
        response.status(201)
        response.json(card)
    })
}

api.findAll = (request, response) => {
    neDB.find({ }).sort({ name: 1 }).exec((exception, cards) => {
        if(exception) {
            const sentence = 'Não foi possível listar os Cards'
            console.error(sentence, exception)

            response.status(exception.status | 400)
            response.json({ 'mensagem': sentence })
        }
        console.log('Lista de Cards', cards)
        response.json(cards)
    })
}


module.exports = api