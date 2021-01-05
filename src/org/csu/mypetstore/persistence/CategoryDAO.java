package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Category;

import java.util.List;

public interface CategoryDAO {
    //获得所有类
    List<Category> getCategoryList();
    //获得一个类，通过ID
    Category getCategory(String categoryId);
}
