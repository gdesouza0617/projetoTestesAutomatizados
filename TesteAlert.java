import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

    private WebDriver driver; //Transformar em uma variável global, aí preciso usar só o driver ao invés do WebDriver nos códigos.

    @Before //Indica que sempre antes de iniciar qualquer teste tem que executar os comandos abaixo.
    public void inicializa(){ //Como todos os testes precisam disso para iniciar, criamos um método para aplicar isso em todos os testes para não precisar ficar repetindo em todos.

        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    }

    @After
    public void finaliza(){

        driver.quit();

    }





    @Test
    public void deveInteragirComAlertSimples() {

        driver.findElement(By.id("alert")).click(); //Procurar e clicar no botão para aparecer o alerta.
        Alert alert = driver.switchTo().alert(); // Transformar o alerta em variável e fazer o driver mudar o foco para ele.
        String texto = alert.getText();  //Transformar o texto do alerta em string
        Assert.assertEquals("Alert Simples", texto); //Verificar se o texto dentro do alerta está correto.
        alert.accept(); //Pra aceitar o aletar e fechar ele.

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto); // Para escrever um nome em um dos campos.


    }

    @Test
    public void deveInteragirComAlertConfirm() {

        // Caso 1 = Aceitar o alerta dando o ok.
        driver.findElement(By.id("confirm")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alerta.getText());
        alerta.accept(); //Comando para aceitar o alerta.
        Assert.assertEquals("Confirmado", alerta.getText());
        alerta.accept();

        //Caso 2 = Cancelar o alerta clicando em cancelar.
        driver.findElement(By.id("confirm")).click();
        alerta = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alerta.getText());
        alerta.dismiss(); //Comando para cancelar o alerta.
        Assert.assertEquals("Negado", alerta.getText());
        alerta.dismiss();


    }

    @Test
    public void deveInteragirComAlertPrompt() {

        driver.findElement(By.id("prompt")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("12");
        alerta.accept();
        Assert.assertEquals("Era 12?", alerta.getText());
        alerta.accept();
        Assert.assertEquals(":D", alerta.getText());
        alerta.accept();


    }


}
