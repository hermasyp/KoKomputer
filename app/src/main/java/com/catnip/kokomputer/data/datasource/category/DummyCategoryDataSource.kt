package com.catnip.kokomputer.data.datasource.category

import com.catnip.kokomputer.data.model.Category

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class DummyCategoryDataSource : CategoryDataSource{
    override fun getCategories(): List<Category> {
        return listOf(
            Category(name = "PC Ready", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_pc_ready.png?raw=true?raw=true"),
            Category(name = "Processor", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_processor.png?raw=true"),
            Category(name = "VGA", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_vga.png?raw=true"),
            Category(name = "RAM", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_ram.png?raw=true"),
            Category(name = "PSU", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_psu.png?raw=true"),
            Category(name = "Notebook", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_notebook.png?raw=true"),
            Category(name = "SSD", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_ssd.png?raw=true"),
            Category(name = "Motherboard", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_motherboard.png?raw=true"),
            Category(name = "Hard disk", imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main//category_img/ic_harddisk.png?raw=true"),
        )
    }

}