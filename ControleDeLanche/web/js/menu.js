function removerLanche(numero){
    
    let remove ={user:sessionStorage.getItem("usuario"),senha:sessionStorage.getItem("pass"), lanche:numero}
    api.post('/ExcluirLanche',remove).then(function (x){
        alert(x.data.retorno)
        inserirCardPedido();
    })
}