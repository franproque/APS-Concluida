const api = axios.create({
    baseURL:'http://localhost:8080/ControleDeLanche',
    headers: {'X-Custom-Header': 'foobar'}
})

let numero=0;
function encodeImageFileAsURL(element) {
    var file = element.files[0];
    var reader = new FileReader();
    reader.onloadend = function() {
    console.log('RESULT', reader.result)
        document.getElementById('imgFodaFodase').src = reader.result
        document.getElementById('img-fon').value=reader.result;
    }
        reader.readAsDataURL(file);
}

function gravarProduto(){
    var produto={nomeProduto:document.getElementById('txtNome').value,imagemProduto:document.getElementById('img-fon').value ,descricaoProduto:document.getElementById('txtDesc').value};
api.post('/NovoProduto',produto).then(function (x){
    alert(x.data.retorno)
    carregarProdutos();
})    
}

function carregarProdutos(){
    let login = {user:"root",senha:""}
    api.post('/ListarProdutos',login).then(function (x){
        let op = document.getElementById('inputGroupSelect01')
        op.innerHTML="<option value='0'>SELECIONE UM PRODUTO</option>";
        x.data.forEach(function (y){
        var opt = document.createElement("option");
        opt.value= y.codProduto;
       
        opt.text = y.nomeProduto;
        op.add(opt, op.options[y.idProduto]);
        })
    })
}
window.onload=carregarProdutos();

function mudaNumero(numero){
    this.numero=numero;
}
function gravarPrecoProduto(){
    
    let cadastro={idProduto:document.getElementById('inputGroupSelect01').value,preco:parseFloat(document.getElementById('txtPreco').value),peso:parseFloat(document.getElementById('txtPeso').value),unMedida:document.getElementById('txtUnidadeMedida').value}
api.post('/CadastroPrecoProduto',cadastro).then(function (x){
    alert(x.data.retorno)
})
}