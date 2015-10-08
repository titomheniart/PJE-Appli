package franck.booklibrary;



/**
 * Created by franc on 08/10/2015.
 */
public class RequestHandler {

    public RequestHandler() {
    }

    public Book getBookByEAN(String ean){
        String title;
        String author;
        String isbn;
        /*
            http://webservices.amazon.com/onca/xml?
Service=AWSECommerceService&
AWSAccessKeyId=RUVnU8rdMHpCoBqN6Rv2chjvV2p7BBGh39kPVxwk&
AssociateTag=AKIAJXVLHNIGLILDHFGQ&
Operation=ItemLookup&
ItemId=ean&
SearchIndex=Electronics&
IdType=EAN
&Timestamp=[YYYY-MM-DDThh:mm:ssZ]
&Signature=[Request Signature]

             */
        return new Book(title, author, isbn);
    }
}
