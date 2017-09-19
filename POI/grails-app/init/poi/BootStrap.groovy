package poi

import fr.mbds.poi.Groupe
import fr.mbds.poi.Image
import fr.mbds.poi.Poi
import fr.mbds.poi.Role
import fr.mbds.poi.User
import fr.mbds.poi.UserRole

class BootStrap {

    def init = { servletContext ->
        def adminrole = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
        def adminuser = new User(username: 'admin',password: 'password').save(flush: true, failOnError: true)
        UserRole.create(adminuser,adminrole,true);

        def adminrole2 = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)
        def adminuser2 = new User(username: 'user',password: 'password').save(flush: true, failOnError: true)
        UserRole.create(adminuser2,adminrole2,true);
        int inx=0
        (1..5).each {
            int groupIndex ->
                def groupeInstance = new Groupe(nom: "Groupe Pois "+groupIndex.toString()).addToImages(new Image(name: "Koala.jpg",url: "koala.jpg"))
                (1..5).each {
                    int poiIndex ->
                        groupeInstance.addToPois( new Poi(nom: "Poi int "+inx, user:adminuser2,  adresse: poiIndex+ ", Route de Colles",lat: 10,lng: 10,description: "description ici "+poiIndex)
                                .addToImages(new Image(name: "Koala"+poiIndex+".jpg",url: "koala"+poiIndex+".jpg")))
                        inx=inx+1
                }
                groupeInstance.save(flush: true, failOnError : true)
        }
    }
    def destroy = {
    }
}