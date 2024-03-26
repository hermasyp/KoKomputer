package com.catnip.kokomputer.data.datasource.product

import com.catnip.kokomputer.data.model.Product

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class DummyProductDataSource : ProductDataSource {
    override fun getProducts(): List<Product> {
        return listOf(
            Product(
                name = "NVIDIA GeForce RTX 3080",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_nvidia_rtx_3080.jpg?raw=true",
                price = 699.99,
                desc = "The NVIDIA GeForce RTX 3080 is a flagship graphics card designed for extreme gaming performance and high-fidelity visuals. With its Ampere architecture, this GPU delivers unparalleled ray-tracing capabilities and blistering frame rates in the latest AAA titles.",
                rating = 4.8
            ),
            Product(
                name = "AMD Ryzen 9 5900X",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_ryzen_9_5900x.png?raw=true",
                price = 549.99,
                desc = "The AMD Ryzen 9 5900X is a powerhouse CPU built for demanding workloads and gaming excellence. Featuring 12 cores and 24 threads, it offers unmatched multitasking performance and responsiveness.",
                rating = 4.9
            ),
            Product(
                name = "ASUS ROG Strix X570-E Gaming",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_asus_rog_x570_gaming.jpeg?raw=true",
                price = 329.99,
                desc = "The ASUS ROG Strix X570-E Gaming motherboard is a feature-packed platform for enthusiast gamers and PC builders. With its AM4 socket and X570 chipset, it supports the latest Ryzen processors and PCIe 4.0 devices, ensuring future-proof performance.",
                rating = 4.7
            ),
            Product(
                name = "Corsair Vengeance RGB Pro 32GB DDR4 RAM",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_corsair_vengeance_rgb_pro.png?raw=true",
                price = 159.99,
                desc = "The Corsair Vengeance RGB Pro is a high-performance DDR4 RAM kit designed for gamers and content creators seeking both speed and style. With a capacity of 32GB and speeds up to 3600MHz, it provides ample memory for multitasking and smooth gameplay.",
                rating = 4.6
            ),
            Product(
                name = "Samsung 970 EVO Plus 1TB NVMe SSD",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_samsung_970_evo.jpg?raw=true",
                price = 149.99,
                desc = "The Samsung 970 EVO Plus NVMe SSD offers lightning-fast storage performance for demanding workloads and gaming applications. With read speeds of up to 3500MB/s and write speeds of up to 3300MB/s, it significantly reduces load times and enhances system responsiveness.",
                rating = 4.9
            ),
            Product(
                name = "Seagate Barracuda 2TB HDD",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_seagate_2tb.jpg?raw=true",
                price = 64.99,
                desc = "The Seagate Barracuda 2TB HDD is a reliable and cost-effective storage solution for mass data storage and backup purposes. With a spacious capacity of 2TB and a 7200 RPM rotational speed, it offers ample space and decent performance for storing large files, games, movies, and more.",
                rating = 4.5
            ),
            Product(
                name = "NZXT Kraken X63 280mm AIO Liquid Cooler",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_nzxt_kraken.jpg?raw=true",
                price = 149.99,
                desc = "The NZXT Kraken X63 is an all-in-one liquid cooler designed for optimal CPU cooling performance and aesthetics. Featuring a 280mm radiator and two Aer P 140mm fans, it provides efficient heat dissipation and quiet operation even under heavy loads.",
                rating = 4.8
            ),
            Product(
                name = "EVGA SuperNOVA 850 G5 80+ Gold PSU",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_evga_supernova.png?raw=true",
                price = 149.99,
                desc = "The EVGA SuperNOVA 850 G5 PSU delivers reliable and efficient power for high-performance gaming rigs and enthusiast systems. With an 80 Plus Gold certification and fully modular design, it offers up to 90% efficiency and customizable cable management options for a clean and clutter-free build.",
                rating = 4.7
            ),
            Product(
                name = "MSI MPG B550 Gaming Edge WiFi",
                imgUrl = "https://raw.githubusercontent.com/hermasyp/kokomputer_assets/main/product_img/img_msi_mpg_b550.webp",
                price = 179.99,
                desc = "The MSI MPG B550 Gaming Edge WiFi motherboard combines performance, reliability, and connectivity for gamers and content creators. Featuring an AM4 socket and B550 chipset, it supports the latest Ryzen processors and PCIe 4.0 devices for lightning-fast data transfer speeds.",
                rating = 4.6
            ),
            Product(
                name = "Intel Core i9-11900K",
                imgUrl = "https://github.com/hermasyp/kokomputer_assets/blob/main/product_img/img_intel_core_i9_11900k.jpg?raw=true",
                price = 529.99,
                desc = "The Intel Core i9-11900K is a flagship CPU designed for extreme gaming performance and multitasking capabilities. With 8 cores and 16 threads, it offers unmatched processing power and responsiveness for demanding workloads and content creation tasks.",
                rating = 4.0
            )
        )
    }
}