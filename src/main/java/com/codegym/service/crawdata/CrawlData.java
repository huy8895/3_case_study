package com.codegym.service.crawdata;

import com.codegym.model.Product;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlData {
    private final String REGEX_PRODUCT = "min-width: 1440px(.*?)set=\"(.*?)\">(.*?)manufacturer\">(.*?)</span>(.*?)</span>(.*?)product-price(.*?)\\$(.*?)</span>";

    public String getContent(File file) {
        String content = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            StringBuilder stringBuilder = new StringBuilder();

            while ((content = reader.readLine()) != null) {
                stringBuilder.append(content);
            }
            content = stringBuilder.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public String getContent(URL url) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder stringBuilder = new StringBuilder();

            while ((content = reader.readLine()) != null) {
                stringBuilder.append(content);
            }
            content = stringBuilder.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public ArrayList<Product> crawlPhoneData(String content) {
        ArrayList<Product> ProductList = new ArrayList<>();
        content = content.replaceAll("\\n+", "");
        Pattern pattern = Pattern.compile(REGEX_PRODUCT);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            Product product = new Product();
            product.setProductImage(matcher.group(2));
            product.setProductName(matcher.group(5));
            product.setProductBrand(matcher.group(4));
            product.setProductLine("Men's Watches");
            Double price = Double.parseDouble(matcher.group(8).replace(",",""));
            product.setProductPrice(price);
            ProductList.add(product);
        }
        return ProductList;
    }

}
