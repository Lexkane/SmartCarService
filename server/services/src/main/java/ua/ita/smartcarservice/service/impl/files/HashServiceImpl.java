package ua.ita.smartcarservice.service.impl.files;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.service.files.HashService;

@Service
public class HashServiceImpl implements HashService {

    @Override
    public String makeHash(Long id, String username, String filename) {
        return DigestUtils.md5Hex(id.toString()+username+filename);
    }
}
