package lk.ijse.supermarketfx.dao.custom.impl;

import lk.ijse.supermarketfx.bo.exception.DuplicateException;
import lk.ijse.supermarketfx.config.FactoryConfiguration;
import lk.ijse.supermarketfx.dao.SQLUtil;
import lk.ijse.supermarketfx.dao.custom.ItemDAO;
import lk.ijse.supermarketfx.entity.Customer;
import lk.ijse.supermarketfx.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 12:43 PM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public class ItemDAOImpl implements ItemDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();//property injection

    @Override
    public List<Item> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<Item> query = session.createQuery("from Item", Item.class);
        List<Item> items = query.list();
        session.close();
        return items;
    }

    @Override
    public String getLastId() {
        return "";
    }

    @Override
    public boolean save(Item item) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (item == null || item.getId() == null) {
                throw new IllegalArgumentException("Invalid item entity or ID is null");
            }

            Item existItem = session.get(Item.class,item.getId());
            if (existItem != null) {
                throw new DuplicateException("Item ID is duplicated");
            }
            
            session.persist(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Item item) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (item == null || item.getId() == null) {
                throw new IllegalArgumentException("Invalid item entity or ID is null");
            }

            Item existItem = session.get(Item.class,item.getId());
            if (existItem == null) {
                throw new IllegalArgumentException("Item with ID " + item.getId() + " does not exist");
            }
            session.merge(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Item item = session.get(Item.class, id);
            if (item != null) {
                session.remove(id);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<String> getAllIds() {
        return List.of();
    }

    @Override
    public Optional<Item> findById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean reduceQuantity(String id, int qty) throws SQLException {
        return SQLUtil.execute(
                "update item set quantity = quantity - ? where item_id = ?",
                qty,
                id
        );
    }
}
