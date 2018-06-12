package com.tishina.integration;

import com.tishina.model.Client;
import com.tishina.model.Order;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class IntegrationServiceTest {
    File file;
    IntegrationService.AuthorBookRow testRowFirst, testRowSecond;

    @Before
    public void createFile() {
        file = new File("src/test/resources/import_test.txt");
        testRowFirst = new IntegrationService.AuthorBookRow(
                "Гарри Поттер и философский камень",
                "200",
                "Джоан Роулинг");
        testRowSecond = new IntegrationService.AuthorBookRow(
                "Гарри Поттер и кубок огня",
                "300",
                "Джоан Роулинг");
    }

    @Test
    public void testFileParser() {
        Assert.assertEquals(true, file.exists());

        IntegrationService service = new IntegrationService();
        List<IntegrationService.AuthorBookRow> rows = service.parseInputFile(file);
        Assert.assertEquals(rows.size(), 4);

        Assert.assertEquals(testRowFirst, rows.get(0));
        Assert.assertEquals(testRowSecond, rows.get(1));
    }


    public void sendEmail() {
        Client client = new Client(null, "Колюсик", null);
        Order order = new Order(client, Collections.emptyList());
        SendMailTLS.sendMail(order);
    }
}
