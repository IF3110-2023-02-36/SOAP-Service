from template_faker import *

def order_price(product_details : list[tuple[int, str, int, int]]):
    price = 0

    for detail in product_details:
        price += detail[2] * detail[3]

    return price

def make_pesanan_detail(PRODUCT_COUNT : int, ORDER_ID : int, PRICE_LOW : int, PRICE_HIGH : int, QUANTITY_LOW : int, QUANTITY_HIGH : int):
    pesanan_detail = []
    
    for i in range(PRODUCT_COUNT):
        id_pesanan = ORDER_ID
        nama_produk = faker.name()
        quantity = randint(QUANTITY_LOW, QUANTITY_HIGH)
        price = randint(PRICE_LOW, PRICE_HIGH)
        
        detail = (id_pesanan, nama_produk, quantity, price)
        pesanan_detail.append(detail)
    
    return pesanan_detail

def pesanan_faker(UNPICKED_COUNT : int, PICKED_COUNT : int, PHP_USER_LIMIT : int, REST_USER_LIMIT : int, PRODUCT_LIMIT : int, PRICE_LOW : int, PRICE_HIGH : int, QUANTITY_LOW : int, QUANTITY_HIGH : int, COST_LOW : int, COST_HIGH : int):
    # Get data from database
    select_phpusers = '''SELECT id, name
                        FROM users
                        ORDER BY rand()
                        LIMIT ''' + str(PHP_USER_LIMIT)
    phpcursor.execute(select_phpusers)
    phpusers = phpcursor.fetchall()
    
    select_restusers = '''SELECT id, name
                        FROM User
                        ORDER BY rand()
                        LIMIT ''' + str(REST_USER_LIMIT)
    restcursor.execute(select_restusers)
    restusers = restcursor.fetchall()

    print("Data retrieval success")
    
    pesanan_sql = '''INSERT INTO pesanan(id_kurir, nama_kurir, id_pemesan, alamat, nama_penerima, status, keterangan, harga, biaya_pengiriman)
                VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s)'''
    pesanan_detail_sql = '''INSERT INTO detail_pesanan(id_pesanan, nama_produk, quantity, harga)
                VALUES(%s, %s, %s, %s)'''
                
    status_type = ['pickup', 'transit']
    
    for i in range(UNPICKED_COUNT):
        product_count = randint(1, PRODUCT_LIMIT)
        php_user_index = randint(0, PHP_USER_LIMIT - 1)
        pesanan_detail = make_pesanan_detail(product_count, 0, PRICE_LOW, PRICE_HIGH, QUANTITY_LOW, QUANTITY_HIGH)

        id_kurir = 0
        nama_kurir = "NULL"
        id_pemesan = phpusers[php_user_index][0]
        alamat = faker.address()
        nama_penerima = phpusers[php_user_index][1]
        status = "searching_courier"
        keterangan = "lagi nyari kurir buat kamu, sabar ya"
        harga = order_price(pesanan_detail)
        biaya_pengiriman = randint(COST_LOW, COST_HIGH)
        
        pesanan_val = (id_kurir, nama_kurir, id_pemesan, alamat, nama_penerima, status, keterangan, harga, biaya_pengiriman)

        cursor.execute(pesanan_sql, pesanan_val)

        pesanan_id = cursor.lastrowid
        
        for j in range(len(pesanan_detail)):
            new_pesanan_detail = (pesanan_id, pesanan_detail[j][1], pesanan_detail[j][2], pesanan_detail[j][3])
            cursor.execute(pesanan_detail_sql, new_pesanan_detail)
            
    for i in range(PICKED_COUNT):
        product_count = randint(1, PRODUCT_LIMIT)
        php_user_index = randint(0, PHP_USER_LIMIT - 1)
        rest_user_index = randint(0, REST_USER_LIMIT - 1)
        pesanan_detail = make_pesanan_detail(product_count, 0, PRICE_LOW, PRICE_HIGH, QUANTITY_LOW, QUANTITY_HIGH)
        status_index = randint(0, 1)

        id_kurir = restusers[rest_user_index][0]
        nama_kurir = restusers[rest_user_index][1]
        id_pemesan = phpusers[php_user_index][0]
        alamat = faker.address()
        nama_penerima = phpusers[php_user_index][1]
        status = status_type[status_index]
        keterangan = "sedang diantar"
        harga = order_price(pesanan_detail)
        biaya_pengiriman = randint(COST_LOW, COST_HIGH)
        
        pesanan_val = (id_kurir, nama_kurir, id_pemesan, alamat, nama_penerima, status, keterangan, harga, biaya_pengiriman)

        cursor.execute(pesanan_sql, pesanan_val)

        pesanan_id = cursor.lastrowid
        
        for j in range(len(pesanan_detail)):
            new_pesanan_detail = (pesanan_id, pesanan_detail[j][1], pesanan_detail[j][2], pesanan_detail[j][3])
            cursor.execute(pesanan_detail_sql, new_pesanan_detail)
        
    db.commit()
    print("Insertion success")
    
if __name__ == "__main__":
    UNPICKED_COUNT = 10
    PICKED_COUNT = 20
    PHP_USER_LIMIT = 10
    REST_USER_LIMIT = 10
    PRODUCT_COUNT = 10
    PRICE_LOW = 1
    PRICE_HIGH = 100
    QUANTITY_LOW = 1
    QUANTITY_HIGH = 10
    COST_LOW = 1
    COST_HIGH = 10
    pesanan_faker(UNPICKED_COUNT, PICKED_COUNT, PHP_USER_LIMIT, REST_USER_LIMIT, PRODUCT_COUNT, PRICE_LOW, PRICE_HIGH, QUANTITY_LOW, QUANTITY_HIGH, COST_LOW, COST_HIGH)