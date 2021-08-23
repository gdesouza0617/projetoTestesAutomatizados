import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestesFramesEJanelas {

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
    public void deveInteragirComFrames(){
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();

        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);


    }

    @Test

    public void deveInteragirComJanelas(){

        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup"); //Colocar o foco para PopUp simples que possui título.
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close(); //Fecha somente a janela, que no caso é a PopUp.
        driver.switchTo().window(""); //Para voltar o foco para a janela principal.
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");

    }

    @Test

    public void deveInteragirComJanelasSemTitulo() {

        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println(driver.getWindowHandle()); // Mostra o id da janela principal
        System.out.println(driver.getWindowHandles()); // Mostra o id da janela principal e da PopUp sem título, que é o 2º. Vamos usar ele para fazer ações.
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]); //Maneira de passar o foco para a PopUp sem título.
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]); //Maneira de passar o foco de volta para a tela principal.
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");

    }
}
