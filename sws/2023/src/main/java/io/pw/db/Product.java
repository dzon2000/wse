package io.pw.db;

/**
 * Created by pwykowski
 */
public record Product(long id, String name, String desc, String serialNumber, int quantity) {
}
