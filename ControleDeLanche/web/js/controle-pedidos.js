let tb = document.getElementById('tbody')
 const api = axios.create({
        baseURL:'http://localhost:8080/ControleDeLanche',
        headers: {'X-Custom-Header': 'foobar'}
    })
   
function getPedidos(){
 let tb= document.getElementById('tbody');  
 tb.innerHTML="";
    api.post('/relatorio')
            .then(function (x){
                let tb= document.getElementById('tbody');  

             x.data.forEach(element => {
                addRow(
                    element['nome'],
                    element['codPedido'],
                    element['valorTotal'],
                    element['dataSaida'],
                    element['dataEntregue'],
                )
                
        })
    
    })
      
       
}

function addRow(cliente, pedido, valor, dtSaida, dtEntrega) {

    if (dtSaida == '' && dtEntrega == '') {
        dtSaida = 'Pendente'
        dtEntrega = 'Pendente'
    }

    tb.innerHTML +=
    "<tr>" +
    "<td>"+cliente+"</td>" +
    "<td>"+pedido+"</td>" +
    "<td>"+valor+"</td>" +
    
    "<td>"+dtSaida+"</td>" +
    "<td>"+dtEntrega+"</td>" +
    "<td>" +
    "<a  onclick='confirmaSaida("+pedido+" )'><i class='fas fa-shipping-fast'></i></a>" +
    "<a  onclick='confirmaEntrega("+pedido+")'><i class='far fa-thumbs-up'></i></a> " +
    "</td>"+
    "</tr>"
}
window.onload=getPedidos();

function confirmaSaida(numero) {
      let vai = {pedido:numero,tipo:1}
  api.post('/SaidaParaEntrega',vai)
    getPedidos();
}

function confirmaEntrega(numero){
   //inserir no BD
   
   
     let vai = {pedido:numero,tipo:2}
  api.post('/SaidaParaEntrega',vai)
    getPedidos();
}