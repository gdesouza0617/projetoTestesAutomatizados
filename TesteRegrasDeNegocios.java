import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteRegrasDeNegocios {

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
    public void deveValidarNomeObrigatorio(){

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
   }

    @Test
    public void deveValidarSobrenomeObrigatorio() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());

    }

    @Test
    public void deveValidarSexoObrigatorio() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Feijão");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());

    }

    @Test
    public void deveValidarComidaVegetariana() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Feijão");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());

    }


    @Test
    public void deveValidarEsportistaIndeciso() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Feijão");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());

    }



}
