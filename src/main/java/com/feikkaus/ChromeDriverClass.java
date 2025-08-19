package com.feikkaus;

import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.ForField.Write;

public class ChromeDriverClass {

        public static void main(String[] args) {

                System.setProperty("webdriver.chrome.driver",
                                "C:\\Users\\ilari\\Downloads\\chromedriver-win64(1)\\chromedriver-win64\\chromedriver.exe");

                WebDriver driver = new ChromeDriver();

                try {
                        driver.get("https://www.veikkaus.fi/fi/tulokset");

                        // Odotetaan että nappi näkyy sivulla (max 10 sekuntia)
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                        // evästeet nappi
                        WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.id("gravitoLightCMP-layer1-reject-all")));
                        rejectButton.click();
                        System.out.println("Klikattiin 'Jatka välttämättömillä'");

                        // klikataan eruojackpot kohasta
                        WebElement jackpot = driver.findElement(
                                        By.xpath("//*[@id=\"results\"]/div/div/div/div/div[1]/div/button[2]/div"));
                        jackpot.click();

                        // löytyykö tuloksia tältä viikolta
                        WebElement element = driver
                                        .findElement(By.cssSelector("div.Notification-module_content__9OWys"));

                        if (element.getText().equals("Ei tuloksia")) {
                                HashMap<String, Integer> results1 = new HashMap<>();
                                HashMap<String, Integer> results2 = new HashMap<>();

                                for (int count = 0; count < 53; count++) {

                                        WebElement prevWeekBtn = driver
                                                        .findElement(By.cssSelector(
                                                                        "button.NavButton-module_center__Z4M53"));
                                        prevWeekBtn.click();

                                        // ootetaan että varmasti numerot on ladattu
                                        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
                                        wait2.until(ExpectedConditions.visibilityOfElementLocated(
                                                        By.cssSelector("div.DrawGameResults-module__numberContainer--_6ZFH")));

                                        // haetaan noi containerit jossa numerot on
                                        List<WebElement> containers = driver.findElements(
                                                        By.cssSelector("div.DrawGameResults-module__numberContainer--_6ZFH"));

                                        // haetaa numerot jokaisen containerin sisätlä
                                        for (WebElement container : containers) {
                                                /*
                                                 * Luodaan List<WebElement> tässä loopin sisällä, koska jos listat
                                                 * luodaan loopin ulkopuolella, vanhat elementit menettävät pätevyyden
                                                 * DOM-muutoksen jälkeen (StaleElementReferenceException).
                                                 */
                                                List<WebElement> primary = container.findElements(
                                                                By.cssSelector("div.DrawGameResults-module__drawGameResult--ejackpot--primary--C1wWf"));

                                                List<WebElement> secondary = container.findElements(
                                                                By.cssSelector("div.DrawGameResults-module__drawGameResult--ejackpot--secondary--w0o4t"));

                                                for (WebElement num : primary) {
                                                        if (results1.containsKey(num.getText())) {
                                                                Integer newValue = results1.get(num.getText()) + 1;
                                                                results1.replace(num.getText(), newValue);
                                                        } else {
                                                                results1.put(num.getText(), 1);
                                                        }

                                                }
                                                for (WebElement second : secondary) {
                                                        if (results2.containsKey(second.getText())) {
                                                                Integer newValue = results2.get(second.getText()) + 1;
                                                                results2.replace(second.getText(), newValue);
                                                        } else {
                                                                results2.put(second.getText(), 1);
                                                        }
                                                }

                                        }
                                }

                                // System.out.println(results1.toString());
                                // System.out.println(results1.toString());

                                System.out.println(SortArray.sortHashMapPrimary(results1));
                                System.out.println(SortArray.sortHashMapSecondary(results2));

                                List<String> prim = SortArray.sortHashMapPrimary(results1);
                                List<String> sec = SortArray.sortHashMapSecondary(results2);

                                List<String> result = new ArrayList<>();
                                result.addAll(prim);
                                result.add("*****************");
                                result.addAll(sec);

                                WriteToFile.writeListToFile(result, "tulokset.txt");

                        } else {
                                System.out.println("***************");
                        }

                } catch (

                Exception e) {
                        e.printStackTrace();
                } finally {
                        driver.quit(); // sulkee selaimen lopuksi
                }
        }
}