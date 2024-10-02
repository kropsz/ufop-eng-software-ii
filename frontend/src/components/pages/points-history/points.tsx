import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {
  Container,
  Title,
  Table,
  TableHead,
  TableRow,
  TableHeader,
  TableCell
} from './points.styles';

interface PointsHistory {
  pointsAdded: number;
  nfeId: string;
  date: string;
}

const PointsHistoryPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [pointsHistory, setPointsHistory] = useState<PointsHistory[]>([]);

  useEffect(() => {
    const fetchPointsHistory = async () => {
      try {
        const response = await axios.get<PointsHistory[]>(`http://localhost:8080/client/points-history/${id}`);
        setPointsHistory(response.data);
      } catch (error) {
        console.error('Error fetching points history:', error);
      }
    };
    fetchPointsHistory();
  }, [id]);

  return (
    <Container>
      <Title>Histórico de Pontos</Title>
      <Table>
        <TableHead>
          <TableRow>
            <TableHeader>Points Added</TableHeader>
            <TableHeader>NFE ID</TableHeader>
            <TableHeader>Date</TableHeader>
          </TableRow>
        </TableHead>
        <tbody>
          {pointsHistory.map((history) => (
            <TableRow key={history.nfeId}>
              <TableCell>{history.pointsAdded}</TableCell>
              <TableCell>{history.nfeId}</TableCell>
              <TableCell>{new Date(history.date).toLocaleString()}</TableCell>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default PointsHistoryPage;