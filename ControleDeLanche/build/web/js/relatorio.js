const api = axios.create({
    baseURL:'http://localhost:8080/ControleDeLanche',
    headers: {'X-Custom-Header': 'foobar'}
})

  function carregarRelatorio(){
 api.post('/relatorio').then(function (x){
    let tb= document.querySelector('tbody');
    var cont =0;
    let total =0;
    x.data.forEach(element => {
        cont+=1;
        tb.innerHTML+=`<tr>
        <th scope="row">${cont}</th>
        <td>${element.nome}</td>
        <td>${element.codPedido}</td>
        <td>R$ ${element.valorTotal}</td>
      </tr>`
       total+=element.valorTotal
    });
    document.querySelector('span').innerHTML=total;

}) 
   
}
window.onload=carregarRelatorio();