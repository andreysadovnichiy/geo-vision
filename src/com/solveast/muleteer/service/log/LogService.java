package com.solveast.muleteer.service.log;

import com.solveast.muleteer.model.Log;
import com.solveast.muleteer.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	public void add(final String string) {
		final Log log = new Log();
		log.setMessage(string);
		logRepository.saveAndFlush(log);
	}

	public List<String> getAll() {
		final List<String> logsStr = new ArrayList<String>();
		final List<Log> logs = logRepository.findAll();

		for (final Log log : logs) {
			logsStr.add(log.getMessage());
		}
		return logsStr;
	}
}
