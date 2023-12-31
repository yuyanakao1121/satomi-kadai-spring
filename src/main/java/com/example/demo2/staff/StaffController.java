package com.example.demo2.staff;

import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.FileCopyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/staff")

public class StaffController {
    @Autowired
    private StaffRepository repository;

    @GetMapping
    public List<Staff> getAllUsers(){
        List<Staff> staff = repository.findAll();
        return staff;
    }

    @PostMapping
    public ResponseEntity<String> registerStaff(@RequestBody Staff staff) {
        try {
            String name = staff.getName();
            String email = staff.getEmail();
            String profileImage = staff.getProfileImage(); // プロフィール画像のBase64データ

        // Base64データをデコードして画像ファイルに保存
        byte[] decodedImage = Base64.decodeBase64(profileImage);
        String filePath = "/Users/nakaoyuya/road_to_geek/no-modules/vue-project/src/components/icons/";
        String fileName = UUID.randomUUID().toString() + ".png";
        String fullFilePath = filePath + fileName;
        FileCopyUtils.copy(decodedImage, new FileOutputStream(fullFilePath));

        staff.setName(name);
        staff.setEmail(email);
        staff.setProfileImage(fileName); // 保存先のパスとファイル名を設定
        repository.save(staff);
            
            return ResponseEntity.ok("登録が成功しました");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("登録エラーが発生しました");
        }
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable("id") int id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("削除が成功しました");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("削除エラーが発生しました");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStaff(@PathVariable("id") int id, @RequestBody Staff updatedStaff) {
    try {
        Staff staff = repository.findById(id);
        if (staff == null) {
            return ResponseEntity.notFound().build();
        }
        
        staff.setName(updatedStaff.getName());
        staff.setEmail(updatedStaff.getEmail());
        repository.save(staff);
        
        return ResponseEntity.ok("更新が成功しました");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新エラーが発生しました");
    }
}

}
