
let url="http://localhost:8080/ControleDeLanche"

const api = axios.create({
    baseURL:'http://localhost:8080/ControleDeLanche',
    headers: {'X-Custom-Header': 'foobar'}
})
onload=inserirCardPedido;
function inserirCardPedido() {
    
    
    dados={user:sessionStorage.getItem("usuario"),senha:sessionStorage.getItem("pass")};
    let card = document.querySelector('.cards')
    card.innerHTML ="";
    api.post('/ListarLanche',dados).then(function(x){
        x.data.forEach(function (t){
              let cards = `  <div class="card gerado dentro" id="${t.codLanche}card" style="background-image: url(img/bgHam.jpg); background-size: cover; background-position: center;">

                <div>
                 <h2>Lanche - ${t.codLanche} </h2>
                 <button onclick="removerLanche(${t.codLanche})">Remover Lanche</button>
                 </div>
             </div>`
            //let cards = '<div class="card gerado" style="background-image: url(img/bgHam.jpg); background-size: cover; background-position: center;"> </div>';
              card.innerHTML += cards;
        })
    })
  
  

}

function montarNovoLanche(){
    window.location.href=`${url}/MonteSeuLanche` 
}
/*
function trazerCards() {
    let card = document.querySelector('.cards')
    let cards = '<div class="card gerado" style="background-image: url(../img/bgHam.jpg); background-size: cover; background-position: center;"></div>'
    
    if (localStorage.getItem('contador') === 0) {return false}
    else{
        for (let i = 0; i<localStorage.getItem('contador'); i++) {
            card.innerHTML += cards
        }
    }
    
    
}
*/


function finalizarPedido() {
  dados={user:sessionStorage.getItem("usuario"),senha:sessionStorage.getItem("pass")};
  api.post('/FinalizaPedido',dados).then(function (x){
      console.log()
      let card = document.querySelector('.cards')
      card.innerHTML='';
      alert(x.data.ret);
  })
}

function contadorDiv() {
    let contador  = document.querySelectorAll('.gerado').length
    console.log(contador)
    localStorage.setItem('contador', contador)
}

