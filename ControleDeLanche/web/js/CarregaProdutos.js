//variavel q guarda o ultimo lanche feito
let ultimoLanche= null;
var itensLanche=[];
let valorTotalLanche=0;
function voltarAoMenu(){
  window.location.href="menu.html"
}
 async function pegaUltimoLanche(){
   try{
 let resposta= await api.post('/ultimolanche',login);
 
    this.ultimoLanche = resposta.data.ultimoId+1;
    
 console.log()
 console.log(this.ultimoLanche)
   }catch(e){
       console.log(e);
   }    
                       
}

let lanche={user:sessionStorage.getItem("usuario"),senha:sessionStorage.getItem("pass")};
const api = axios.create({
    baseURL:'http://localhost:8080/ControleDeLanche',
    headers: {'X-Custom-Header': 'foobar'}
})


var login={user:sessionStorage.getItem("usuario"),senha:sessionStorage.getItem("pass"),cpf:sessionStorage.getItem("usuario")};

console.log(login)

window.onload= pegaUltimoLanche(),api.post('/ingredientesJSON',login).then(
    (x)=>{
        x.data.forEach(element => {
          let mainCard = document.querySelector('.container');
         
          mainCard.innerHTML+=`  <div class="card" style="background-image: url('img/bgHam.jpg'); background-size: cover; background-position: center;">
          <div class="card-body">
              <div class="card-food" id=${element.idProduto}>
                  <img src="${element.imagemProduto}" alt="">
                  <h2>${element.nomeProduto}</h2>
                  <span>
                      <h3>${element.pesoProduto}${element.unMedida} </h3>
                      <h3>R$ ${element.precoProduto}</h3>
                      </span>
                      <div class="buttons-add">  
                          <button onclick="aumentaQuantidade(${element.idProduto})">+</button>
                          <input type="number" min="1" name="" id="${element.idProduto}input"value=1 readonly="readonly" >
                          <button onclick="diminuiQuantidade(${element.idProduto})"> -</button>
                      </div>
                      <div class="add-remove">     
                          <button onclick="montaLanche(${element.idProduto},${element.precoProduto})" title="Coloque a quantidde antes de adicionar ao lanche">Adicionar no lanche</button> 
                          </div>
              </div>
          </div>
          
      </div>`
         
         /* mainCard.innerHTML+=`   
            <div class="partial-card" id="${element.idProduto}" >
            <img src="${element.imagemProduto}" alt="">
            <div class="card-header">
                
                <h2>${element.nomeProduto}</h2>
                <p>${element.descricaoProduto}</p>
                <span>
                <h3>${element.pesoProduto}${element.unMedida}</h3>
                <h3>R$${element.precoProduto}</h3>
                </span>
                <div class="buttons-add">  
                <button onclick="aumentaQuantidade(${element.idProduto})">+</button>
                <input type="number" min="1" name="" id="${element.idProduto}input"value=1>
                <button onclick="diminuiQuantidade(${element.idProduto})">-</button>
                </div>
                <div class="add-remove">     
                       <button onclick="montaLanche(${element.idProduto})" title="Coloque a quantidde antes de adicionar ao lanche">Adicionar no lanche</button> 
                       </div>
                
                <div class="card-body"></div>
            </div>
        </div>`*/
        console.log(element.nomeProduto)
        });
    }
)
function aumentaQuantidade(value){
    var input = document.getElementById(value+"input")
    
    valor = parseInt(input.value);
    valor = valor+1
    input.value=valor

}
function diminuiQuantidade(value){
    var input = document.getElementById(value+"input")
    
    valor = parseInt(input.value);
    if(valor>=1){
    valor = valor-1
    input.value=valor
    }
}

//Envia para o banco um item do lanche
function addicionaItem(valor){
   var quant= parseInt(document.getElementById(valor+'input').value)
    let item ={user:sessionStorage.getItem("usuario"),senha:sessionStorage.getItem("pass"),cpfCnpj:sessionStorage.getItem("usuario"),lanche:this.ultimoLanche,quantidade:quant,idItem:valor}
   console.log(item)
    api.post('/InserirItensLanche',item).then(
            function (x){
                alert(x.data.retorno)
            } 
            )
}

function montaLanche(value,preco){
  var prod =  document.getElementById(value);
        valorTotalLanche +=preco*parseInt(document.getElementById(value+'input').value)
        document.getElementById('total-lanche').innerHTML=valorTotalLanche;
       prod.style.border="0.3rem solid green"
       itensLanche.push(parseInt(value));
           addicionaItem(value);
     
 
        
 
}
function incluirLanche(){
    if(1 in this.itensLanche || 2 in this.itensLanche && 3 in this.ItensLanche){
        window.location.href="menu.html"
    }else{
         alert("Escolha um pão e um Hamburgue")
    }
    
    
}