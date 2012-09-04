package models.ny;

import siena.Generator;
import siena.Model;
import siena.Id;
import siena.Query;

/**
 * User: lbouin
 * Date: 20/08/12
 * Time: 18:01
 */
public class ConnectedUser extends Model {

    @Id(Generator.AUTO_INCREMENT)
    public Long id;

    public Long userId;

    public String token;

    public ConnectedUser(Long id, String token){
        this.userId=id;
        this.token=token;
    }

    public static Query<ConnectedUser>all(){
        return Model.all(ConnectedUser.class);
    }

    public static ConnectedUser findByUserId(Long userId){
        return all().filter("userId",userId).get();
    }

}
