package com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;

public interface RenameCategory {
    Category rename(Category category);
}
