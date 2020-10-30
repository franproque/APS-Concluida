const api = axios.create({
    baseURL:'http://localhost:8080/ControleDeLanche',
    headers: {'X-Custom-Header': 'foobar'}
})



function fabricarUsuario(){
    let obj
    let validador = true;
    let name = document.getElementById('txtNome').value;
    let email = document.getElementById('txtEmail').value;
    let senha = document.getElementById('txtSenha').value;
    let senhaConf = document.getElementById('txtSenhaConf').value;
    let = cpf = document.getElementById('txtCPF').value
    let = telefone = document.getElementById('txtTelefone').value
    let dtNasc = document.getElementById('txtDtNascimento').value
    
    let usuario = fabricaDeUsuario(name, senha, senhaConf, email, cpf, telefone, dtNasc)

    
    if(!usuario.validarEmail()) {validador = false}
    if(!usuario.validarSenhas()) {validador = false}
    if(!usuario.validarCPF()) {validador = false}


    if(validador === true){
        obj = usuario.gerarObjeto()
        let data = JSON.stringify(obj)
        api.post('/register', obj).then(function (x){
            console.log(x.data.retorno)
            if(x.data.retorno==="Registrado"){
                window.location.href="index.html"
            }else{
                alert(x.data.retorno)
            }
        })
    }
    
    

    

}



