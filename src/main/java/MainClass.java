import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://ok.ru/settings");

        // авторизация на сайте
        driver.findElement(By.xpath("//div/input[@id='field_email']")).sendKeys("login"); // ввести настоящий логин
        driver.findElement(By.xpath("//div/input[@id='field_password']")).sendKeys("password"); // ввести настоящий пароль
        driver.findElement(By.xpath("//div/input[@type = 'submit']")).click();
        driver.findElement(By.xpath("//div[@class = 'user-settings_i_n'][text() = 'Личные данные']")).click();

        SettingsPage test = new SettingsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);

        // вызов метода "Проверка на сохранение если пустые поля"
        test.checkErrorEmptyFields();
        test.screenshots();

        //вызов метода "Проверка на ввод корректных данных и проверка сохранения"
        test.inputCorrectData();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'Ваши настройки сохранены']")));
        test.screenshots();

        //вызов метода Проверка ввода и сохранения неправильных данных
        test.checkInputIncorrectData();
        test.screenshots();
        }
    }

