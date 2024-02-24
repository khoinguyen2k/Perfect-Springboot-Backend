package com.backend.utilities;

import com.backend.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("constant/")
public class ConstantController {

    @GetMapping("tools/list")
    public ResponseEntity<List<String>> getAllValidStates() {
        return new ResponseEntity<>(Constant.stateList(), HttpStatus.OK);
    }
}
